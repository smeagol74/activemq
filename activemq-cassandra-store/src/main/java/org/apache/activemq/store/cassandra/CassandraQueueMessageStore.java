package org.apache.activemq.store.cassandra;

import com.datastax.driver.core.Session;
import org.apache.activemq.broker.ConnectionContext;
import org.apache.activemq.command.*;
import org.apache.activemq.store.MessageRecoveryListener;
import org.apache.activemq.store.MessageStore;
import org.apache.activemq.usage.MemoryUsage;

import java.io.IOException;
import java.util.concurrent.Future;

/**
 * MessageStore implementation for Cassandra database
 */
public class CassandraQueueMessageStore implements MessageStore {

    public CassandraQueueMessageStore(Session session, ActiveMQQueue destination) {
        // TODO implement org.apache.activemq.store.cassandra.CassandraQueueMessageStore.CassandraQueueMessageStore
    }

    /**
     * Adds a message to the message store
     *
     * @param context context
     * @param message
     * @throws java.io.IOException
     */
    @Override
    public void addMessage(ConnectionContext context, Message message) throws IOException {
        // TODO implement org.apache.activemq.store.cassandra.CassandraQueueMessageStore.addMessage

    }

    /**
     * Adds a message to the message store
     *
     * @param context         context
     * @param message
     * @param canOptimizeHint - give a hint to the store that the message may be consumed before it hits the disk
     * @throws java.io.IOException
     */
    @Override
    public void addMessage(ConnectionContext context, Message message, boolean canOptimizeHint) throws IOException {
        // TODO implement org.apache.activemq.store.cassandra.CassandraQueueMessageStore.addMessage

    }

    /**
     * Adds a message to the message store
     *
     * @param context context
     * @param message
     * @return a Future to track when this is complete
     * @throws java.io.IOException
     * @throws java.io.IOException
     */
    @Override
    public Future<Object> asyncAddQueueMessage(ConnectionContext context, Message message) throws IOException {
        // TODO implement org.apache.activemq.store.cassandra.CassandraQueueMessageStore.asyncAddQueueMessage
        return null;
    }

    /**
     * Adds a message to the message store
     *
     * @param context         context
     * @param message
     * @param canOptimizeHint - give a hint to the store that the message may be consumed before it hits the disk
     * @return a Future to track when this is complete
     * @throws java.io.IOException
     * @throws java.io.IOException
     */
    @Override
    public Future<Object> asyncAddQueueMessage(ConnectionContext context, Message message, boolean canOptimizeHint) throws IOException {
        // TODO implement org.apache.activemq.store.cassandra.CassandraQueueMessageStore.asyncAddQueueMessage
        return null;
    }

    /**
     * Adds a message to the message store
     *
     * @param context context
     * @param message
     * @return a Future to track when this is complete
     * @throws java.io.IOException
     * @throws java.io.IOException
     */
    @Override
    public Future<Object> asyncAddTopicMessage(ConnectionContext context, Message message) throws IOException {
        // TODO implement org.apache.activemq.store.cassandra.CassandraQueueMessageStore.asyncAddTopicMessage
        return null;
    }

    /**
     * Adds a message to the message store
     *
     * @param context         context
     * @param message
     * @param canOptimizeHint - give a hint to the store that the message may be consumed before it hits the disk
     * @return a Future to track when this is complete
     * @throws java.io.IOException
     * @throws java.io.IOException
     */
    @Override
    public Future<Object> asyncAddTopicMessage(ConnectionContext context, Message message, boolean canOptimizeHint) throws IOException {
        // TODO implement org.apache.activemq.store.cassandra.CassandraQueueMessageStore.asyncAddTopicMessage
        return null;
    }

    /**
     * Looks up a message using either the String messageID or the
     * messageNumber. Implementations are encouraged to fill in the missing key
     * if its easy to do so.
     *
     * @param identity which contains either the messageID or the messageNumber
     * @return the message or null if it does not exist
     * @throws java.io.IOException
     */
    @Override
    public Message getMessage(MessageId identity) throws IOException {
        // TODO implement org.apache.activemq.store.cassandra.CassandraQueueMessageStore.getMessage
        return null;
    }

    /**
     * Removes a message from the message store.
     *
     * @param context
     * @param ack     the ack request that cause the message to be removed. It
     *                conatins the identity which contains the messageID of the
     *                message that needs to be removed.
     * @throws java.io.IOException
     */
    @Override
    public void removeMessage(ConnectionContext context, MessageAck ack) throws IOException {
        // TODO implement org.apache.activemq.store.cassandra.CassandraQueueMessageStore.removeMessage

    }

    @Override
    public void removeAsyncMessage(ConnectionContext context, MessageAck ack) throws IOException {
        // TODO implement org.apache.activemq.store.cassandra.CassandraQueueMessageStore.removeAsyncMessage

    }

    /**
     * Removes all the messages from the message store.
     *
     * @param context
     * @throws java.io.IOException
     */
    @Override
    public void removeAllMessages(ConnectionContext context) throws IOException {
        // TODO implement org.apache.activemq.store.cassandra.CassandraQueueMessageStore.removeAllMessages

    }

    /**
     * Recover any messages to be delivered.
     *
     * @param container
     * @throws Exception
     */
    @Override
    public void recover(MessageRecoveryListener container) throws Exception {
        // TODO implement org.apache.activemq.store.cassandra.CassandraQueueMessageStore.recover

    }

    /**
     * The destination that the message store is holding messages for.
     *
     * @return the destination
     */
    @Override
    public ActiveMQDestination getDestination() {
        // TODO implement org.apache.activemq.store.cassandra.CassandraQueueMessageStore.getDestination
        return null;
    }

    /**
     * @param memoeyUSage The SystemUsage that is controlling the
     *                    destination's memory usage.
     */
    @Override
    public void setMemoryUsage(MemoryUsage memoeyUSage) {
        // TODO implement org.apache.activemq.store.cassandra.CassandraQueueMessageStore.setMemoryUsage

    }

    /**
     * @return the number of messages ready to deliver
     * @throws java.io.IOException
     */
    @Override
    public int getMessageCount() throws IOException {
        // TODO implement org.apache.activemq.store.cassandra.CassandraQueueMessageStore.getMessageCount
        return 0;
    }

    /**
     * A hint to the Store to reset any batching state for the Destination
     */
    @Override
    public void resetBatching() {
        // TODO implement org.apache.activemq.store.cassandra.CassandraQueueMessageStore.resetBatching

    }

    @Override
    public void recoverNextMessages(int maxReturned, MessageRecoveryListener listener) throws Exception {
        // TODO implement org.apache.activemq.store.cassandra.CassandraQueueMessageStore.recoverNextMessages

    }

    @Override
    public void dispose(ConnectionContext context) {
        // TODO implement org.apache.activemq.store.cassandra.CassandraQueueMessageStore.dispose

    }

    /**
     * allow caching cursors to set the current batch offset when cache is exhausted
     *
     * @param messageId
     * @throws Exception
     */
    @Override
    public void setBatch(MessageId messageId) throws Exception {
        // TODO implement org.apache.activemq.store.cassandra.CassandraQueueMessageStore.setBatch

    }

    /**
     * flag to indicate if the store is empty
     *
     * @return true if the message count is 0
     * @throws Exception
     */
    @Override
    public boolean isEmpty() throws Exception {
        // TODO implement org.apache.activemq.store.cassandra.CassandraQueueMessageStore.isEmpty
        return false;
    }

    /**
     * A hint to the store to try recover messages according to priority
     *
     * @param prioritizedMessages
     */
    @Override
    public void setPrioritizedMessages(boolean prioritizedMessages) {
        // TODO implement org.apache.activemq.store.cassandra.CassandraQueueMessageStore.setPrioritizedMessages

    }

    /**
     * @return true if store is trying to recover messages according to priority
     */
    @Override
    public boolean isPrioritizedMessages() {
        // TODO implement org.apache.activemq.store.cassandra.CassandraQueueMessageStore.isPrioritizedMessages
        return false;
    }

    @Override
    public void start() throws Exception {
        // TODO implement org.apache.activemq.store.cassandra.CassandraQueueMessageStore.start

    }

    @Override
    public void stop() throws Exception {
        // TODO implement org.apache.activemq.store.cassandra.CassandraQueueMessageStore.stop

    }
}
