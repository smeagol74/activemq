package org.apache.activemq.store.cassandra;

import com.datastax.driver.core.Cluster;
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

import java.io.File;
import java.io.IOException;
import java.util.Set;

/**
 * A {@link org.apache.activemq.store.PersistenceAdapter} implementation using Cassandra for persistence
 * storage.
 *
 * This persistence adapter will correctly remember prepared XA transactions,
 * but it will not keep track of local transaction commits so that operations
 * performed against the Message store are done as a single uow.
 *
 * @org.apache.xbean.XBean element="cassandra"
 *
 *
 */
public class CassandraPersistenceAdapter extends LockableServiceSupport implements PersistenceAdapter {

  private Cluster cluster;

  @Override
  public void init() throws Exception {
    //TODO implement org.apache.activemq.store.cassandra.CassandraPersistenceAdapter.init

  }

  @Override
  public Locker createDefaultLocker() throws IOException {
    //TODO implement org.apache.activemq.store.cassandra.CassandraPersistenceAdapter.createDefaultLocker
    return new CassandraLocker(cluster);
  }

  @Override
  public Set<ActiveMQDestination> getDestinations() {
    //TODO implement org.apache.activemq.store.cassandra.CassandraPersistenceAdapter.getDestinations
    return null;
  }

  @Override
  public MessageStore createQueueMessageStore(ActiveMQQueue destination) throws IOException {
    //TODO implement org.apache.activemq.store.cassandra.CassandraPersistenceAdapter.createQueueMessageStore
    return null;
  }

  @Override
  public TopicMessageStore createTopicMessageStore(ActiveMQTopic destination) throws IOException {
    //TODO implement org.apache.activemq.store.cassandra.CassandraPersistenceAdapter.createTopicMessageStore
    return null;
  }

  @Override
  public void removeQueueMessageStore(ActiveMQQueue destination) {
    //TODO implement org.apache.activemq.store.cassandra.CassandraPersistenceAdapter.removeQueueMessageStore

  }

  @Override
  public void removeTopicMessageStore(ActiveMQTopic destination) {
    //TODO implement org.apache.activemq.store.cassandra.CassandraPersistenceAdapter.removeTopicMessageStore

  }

  @Override
  public TransactionStore createTransactionStore() throws IOException {
    //TODO implement org.apache.activemq.store.cassandra.CassandraPersistenceAdapter.createTransactionStore
    return null;
  }

  @Override
  public void beginTransaction(ConnectionContext context) throws IOException {
    //TODO implement org.apache.activemq.store.cassandra.CassandraPersistenceAdapter.beginTransaction

  }

  @Override
  public void commitTransaction(ConnectionContext context) throws IOException {
    //TODO implement org.apache.activemq.store.cassandra.CassandraPersistenceAdapter.commitTransaction

  }

  @Override
  public void rollbackTransaction(ConnectionContext context) throws IOException {
    //TODO implement org.apache.activemq.store.cassandra.CassandraPersistenceAdapter.rollbackTransaction

  }

  @Override
  public long getLastMessageBrokerSequenceId() throws IOException {
    //TODO implement org.apache.activemq.store.cassandra.CassandraPersistenceAdapter.getLastMessageBrokerSequenceId
    return 0;
  }

  @Override
  public void deleteAllMessages() throws IOException {
    //TODO implement org.apache.activemq.store.cassandra.CassandraPersistenceAdapter.deleteAllMessages

  }

  @Override
  public void setUsageManager(SystemUsage usageManager) {
    //TODO implement org.apache.activemq.store.cassandra.CassandraPersistenceAdapter.setUsageManager

  }

  @Override
  public void setBrokerName(String brokerName) {
    //TODO implement org.apache.activemq.store.cassandra.CassandraPersistenceAdapter.setBrokerName

  }

  @Override
  public void setDirectory(File dir) {
    //TODO implement org.apache.activemq.store.cassandra.CassandraPersistenceAdapter.setDirectory

  }

  @Override
  public File getDirectory() {
    //TODO implement org.apache.activemq.store.cassandra.CassandraPersistenceAdapter.getDirectory
    return null;
  }

  @Override
  public void checkpoint(boolean sync) throws IOException {
    //TODO implement org.apache.activemq.store.cassandra.CassandraPersistenceAdapter.checkpoint

  }

  @Override
  public long size() {
    //TODO implement org.apache.activemq.store.cassandra.CassandraPersistenceAdapter.size
    return 0;
  }

  @Override
  public long getLastProducerSequenceId(ProducerId id) throws IOException {
    //TODO implement org.apache.activemq.store.cassandra.CassandraPersistenceAdapter.getLastProducerSequenceId
    return 0;
  }

  @Override
  protected void doStop(ServiceStopper stopper) throws Exception {
    //TODO implement org.apache.activemq.store.cassandra.CassandraPersistenceAdapter.doStop

  }

  @Override
  protected void doStart() throws Exception {
    //TODO implement org.apache.activemq.store.cassandra.CassandraPersistenceAdapter.doStart

  }
}
