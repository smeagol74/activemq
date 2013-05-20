package org.apache.activemq.store.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import org.apache.activemq.broker.BrokerServiceAware;
import org.apache.activemq.broker.ConnectionContext;
import org.apache.activemq.broker.LockableServiceSupport;
import org.apache.activemq.broker.Locker;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.command.ProducerId;
import org.apache.activemq.store.MessageStore;
import org.apache.activemq.store.PersistenceAdapter;
import org.apache.activemq.store.TopicMessageStore;
import org.apache.activemq.store.TransactionStore;
import org.apache.activemq.usage.SystemUsage;
import org.apache.activemq.util.ServiceStopper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A {@link org.apache.activemq.store.PersistenceAdapter} implementation using Cassandra for persistence
 * storage.
 * <p/>
 * This persistence adapter will correctly remember prepared XA transactions,
 * but it will not keep track of local transaction commits so that operations
 * performed against the Message store are done as a single uow.
 *
 * @org.apache.xbean.XBean element="cassandra"
 */
public class CassandraPersistenceAdapter extends LockableServiceSupport implements PersistenceAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(CassandraPersistenceAdapter.class);

    private Cluster cluster;
    private Session session;
    private String node = "127.0.0.1";
    private String keyspace = "activemq";
    private String strategy = "SimpleStrategy";
    private int replicationFactor = 1;
    private final ConcurrentHashMap<String, CassandraQueueMessageStore> queues = new ConcurrentHashMap<String, CassandraQueueMessageStore>();
    private final ConcurrentHashMap<String, CassandraTopicMessageStore> topics = new ConcurrentHashMap<String, CassandraTopicMessageStore>();

    private CassandraStorage storage;

    private Session initSession() {
        Session result;
        try {
            result = cluster.connect(keyspace);
        } catch (Exception e) {
            result = null;
        }
        if (result == null) {
            result = cluster.connect();
            result.execute(String.format("CREATE KEYSPACE %s WITH replication = {'class': '%s', 'replication_factor': %d};", keyspace, strategy, replicationFactor));
            result.shutdown();
            result = cluster.connect(keyspace);
            createSchema(result);
        }
        return result;
    }

    private void createSchema(Session session) {
        session.execute("CREATE TABLE songs (" +
                "id uuid PRIMARY KEY," +
                "title text," +
                "album text," +
                "artist text," +
                "tags set<text>," +
                "data blob" +
                ");");
        session.execute("CREATE TABLE playlists (" +
                "id uuid," +
                "title text," +
                "album text," +
                "artist text," +
                "song_id uuid," +
                "PRIMARY KEY(id, title, album, artist)" +
                ");");
    }

    /**
     * Initialize resources before locking
     *
     * @throws Exception
     */
    @Override
    public void init() throws Exception {
        cluster = Cluster.builder()
                .addContactPoint(node)
                .build();
        session = initSession();
        storage = new CassandraStorage(session);
        loadDestinations();
    }

    private void loadDestinations() {
        // TODO implement org.apache.activemq.store.cassandra.CassandraPersistenceAdapter.loadDestinations
    }

    /**
     * Create a default locker
     *
     * @return default locker
     * @throws java.io.IOException
     */
    @Override
    public Locker createDefaultLocker() throws IOException {
        return new CassandraLocker(session);
    }

    /**
     * Returns a set of all the {@link org.apache.activemq.command.ActiveMQDestination}
     * objects that the persistence store is aware exist.
     *
     * @return active destinations
     */
    @Override
    public Set<ActiveMQDestination> getDestinations() {
        Set<ActiveMQDestination> result = new HashSet<ActiveMQDestination>();
        synchronized (queues) {
            for (CassandraQueueMessageStore store: queues.values()) {
                result.add(store.getDestination());
            }
        }
        synchronized (topics) {
            for (CassandraTopicMessageStore store: topics.values()) {
                result.add(store.getDestination());
            }
        }
        return result;
    }

    /**
     * Factory method to create a new queue message store with the given destination name
     *
     * @param destination
     * @return the message store
     * @throws java.io.IOException
     */
    @Override
    public MessageStore createQueueMessageStore(ActiveMQQueue destination) throws IOException {
        CassandraQueueMessageStore result = null;
        String queueName = destination.getQualifiedName();
        synchronized (queues) {
            if (!queues.containsKey(queueName)) {
                result = new CassandraQueueMessageStore(session, destination);
                queues.put(queueName, result);
            }
        }
        if (result == null) {
            result = queues.get(queueName);
        } else {
            storage.queues.save(destination);
        }
        return result;
    }

    /**
     * Factory method to create a new topic message store with the given destination name
     *
     * @param destination
     * @return the topic message store
     * @throws java.io.IOException
     */
    @Override
    public TopicMessageStore createTopicMessageStore(ActiveMQTopic destination) throws IOException {
        CassandraTopicMessageStore result = null;
        String topicName = destination.getQualifiedName();
        synchronized (topics) {
            if (topics.containsKey(topicName)) {
                result = new CassandraTopicMessageStore(session, destination);
                topics.put(topicName, result);
            }
        }
        if (result == null) {
            result = topics.get(topicName);
        } else {
            storage.topics.save(destination);
        }
        return result;
    }

    /**
     * Cleanup method to remove any state associated with the given destination.
     * This method does not stop the message store (it might not be cached).
     *
     * @param destination Destination to forget
     */
    @Override
    public void removeQueueMessageStore(ActiveMQQueue destination) {
        try {
            String name = destination.getQualifiedName();
            queues.get(name).removeAllMessages(new ConnectionContext());
            queues.remove(name);
            storage.queues.remove(destination);
        } catch (IOException e) {
            LOG.error("", e);
        }
    }

    /**
     * Cleanup method to remove any state associated with the given destination
     * This method does not stop the message store (it might not be cached).
     *
     * @param destination Destination to forget
     */
    @Override
    public void removeTopicMessageStore(ActiveMQTopic destination) {
        try {
            String name = destination.getQualifiedName();
            topics.get(name).removeAllMessages(new ConnectionContext());
            topics.remove(name);
            storage.topics.remove(destination);
        } catch (IOException e) {
            LOG.error("", e);
        }
    }

    /**
     * Factory method to create a new persistent prepared transaction store for XA recovery
     *
     * @return transaction store
     * @throws java.io.IOException
     */
    @Override
    public TransactionStore createTransactionStore() throws IOException {
        return new CassandraTransactionsStore(session);
    }

    /**
     * This method starts a transaction on the persistent storage - which is nothing to
     * do with JMS or XA transactions - its purely a mechanism to perform multiple writes
     * to a persistent store in 1 transaction as a performance optimization.
     * <p/>
     * Typically one transaction will require one disk synchronization point and so for
     * real high performance its usually faster to perform many writes within the same
     * transaction to minimize latency caused by disk synchronization. This is especially
     * true when using tools like Berkeley Db or embedded JDBC servers.
     *
     * @param context
     * @throws java.io.IOException
     */
    @Override
    public void beginTransaction(ConnectionContext context) throws IOException {
        // Do nothing here, Cassandra not transactional
    }

    /**
     * Commit a persistence transaction
     *
     * @param context
     * @throws java.io.IOException
     * @see org.apache.activemq.store.PersistenceAdapter#beginTransaction(org.apache.activemq.broker.ConnectionContext context)
     */
    @Override
    public void commitTransaction(ConnectionContext context) throws IOException {
        // Do nothing here, Cassandra not transactional
    }

    /**
     * Rollback a persistence transaction
     *
     * @param context
     * @throws java.io.IOException
     * @see org.apache.activemq.store.PersistenceAdapter#beginTransaction(org.apache.activemq.broker.ConnectionContext context)
     */
    @Override
    public void rollbackTransaction(ConnectionContext context) throws IOException {
        // Do nothing here, Cassandra not transactional
    }

    /**
     * @return last broker sequence
     * @throws java.io.IOException
     */
    @Override
    public long getLastMessageBrokerSequenceId() throws IOException {
        // TODO implement org.apache.activemq.store.cassandra.CassandraPersistenceAdapter.getLastMessageBrokerSequenceId
        return 0;
    }

    /**
     * Delete's all the messages in the persistent store.
     *
     * @throws java.io.IOException
     */
    @Override
    public void deleteAllMessages() throws IOException {
        // TODO implement org.apache.activemq.store.cassandra.CassandraPersistenceAdapter.deleteAllMessages

    }

    /**
     * @param usageManager The UsageManager that is controlling the broker's memory usage.
     */
    @Override
    public void setUsageManager(SystemUsage usageManager) {
        // TODO implement org.apache.activemq.store.cassandra.CassandraPersistenceAdapter.setUsageManager

    }

    /**
     * Set the name of the broker using the adapter
     *
     * @param brokerName
     */
    @Override
    public void setBrokerName(String brokerName) {
        // TODO implement org.apache.activemq.store.cassandra.CassandraPersistenceAdapter.setBrokerName

    }

    /**
     * Set the directory where any data files should be created
     *
     * @param dir
     */
    @Override
    public void setDirectory(File dir) {
        // TODO implement org.apache.activemq.store.cassandra.CassandraPersistenceAdapter.setDirectory

    }

    /**
     * @return the directory used by the persistence adaptor
     */
    @Override
    public File getDirectory() {
        // TODO implement org.apache.activemq.store.cassandra.CassandraPersistenceAdapter.getDirectory
        return null;
    }

    /**
     * checkpoint any
     *
     * @param sync
     * @throws java.io.IOException
     */
    @Override
    public void checkpoint(boolean sync) throws IOException {
        // TODO implement org.apache.activemq.store.cassandra.CassandraPersistenceAdapter.checkpoint

    }

    /**
     * A hint to return the size of the store on disk
     *
     * @return disk space used in bytes of 0 if not implemented
     */
    @Override
    public long size() {
        // TODO implement org.apache.activemq.store.cassandra.CassandraPersistenceAdapter.size
        return 0;
    }

    /**
     * return the last stored producer sequenceId for this producer Id
     * used to suppress duplicate sends on failover reconnect at the transport
     * when a reconnect occurs
     *
     * @param id the producerId to find a sequenceId for
     * @return the last stored sequence id or -1 if no suppression needed
     */
    @Override
    public long getLastProducerSequenceId(ProducerId id) throws IOException {
        // TODO implement org.apache.activemq.store.cassandra.CassandraPersistenceAdapter.getLastProducerSequenceId
        return 0;
    }

    @Override
    protected void doStop(ServiceStopper stopper) throws Exception {
        // TODO implement org.apache.activemq.store.cassandra.CassandraPersistenceAdapter.doStop

    }

    @Override
    protected void doStart() throws Exception {
        // TODO implement org.apache.activemq.store.cassandra.CassandraPersistenceAdapter.doStart

    }
}
