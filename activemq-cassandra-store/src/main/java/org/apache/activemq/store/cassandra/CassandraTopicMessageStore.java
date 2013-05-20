package org.apache.activemq.store.cassandra;

import com.datastax.driver.core.Session;
import org.apache.activemq.broker.ConnectionContext;
import org.apache.activemq.command.*;
import org.apache.activemq.store.MessageRecoveryListener;
import org.apache.activemq.store.TopicMessageStore;
import org.apache.activemq.usage.MemoryUsage;

import java.io.IOException;
import java.util.concurrent.Future;

/**
 * User: smeagol
 * Date: 18.05.13
 * Time: 14:52
 */
public class CassandraTopicMessageStore implements TopicMessageStore {
    public CassandraTopicMessageStore(Session session, ActiveMQTopic destination) {
        // TODO implement org.apache.activemq.store.cassandra.CassandraTopicMessageStore.CassandraTopicMessageStore
    }

    /**
     * Stores the last acknowledged messgeID for the given subscription so that
     * we can recover and commence dispatching messages from the last checkpoint
     *
     * @param context
     * @param clientId
     * @param subscriptionName
     * @param messageId
     * @param subscriptionPersistentId
     * @throws java.io.IOException
     */
    @Override
    public void acknowledge(ConnectionContext context, String clientId, String subscriptionName, MessageId messageId, MessageAck ack) throws IOException {
        // TODO implement org.apache.activemq.store.cassandra.CassandraTopicMessageStore.acknowledge

    }

    /**
     * @param clientId
     * @param subscriptionName
     * @param sub
     * @throws java.io.IOException
     * @throws javax.jms.JMSException
     */
    @Override
    public void deleteSubscription(String clientId, String subscriptionName) throws IOException {
        // TODO implement org.apache.activemq.store.cassandra.CassandraTopicMessageStore.deleteSubscription

    }

    /**
     * For the new subscription find the last acknowledged message ID and then
     * find any new messages since then and dispatch them to the subscription.
     * <p/> e.g. if we dispatched some messages to a new durable topic
     * subscriber, then went down before acknowledging any messages, we need to
     * know the correct point from which to recover from.
     *
     * @param clientId
     * @param subscriptionName
     * @param listener
     * @param subscription
     * @throws Exception
     */
    @Override
    public void recoverSubscription(String clientId, String subscriptionName, MessageRecoveryListener listener) throws Exception {
        // TODO implement org.apache.activemq.store.cassandra.CassandraTopicMessageStore.recoverSubscription

    }

    /**
     * For an active subscription - retrieve messages from the store for the
     * subscriber after the lastMessageId messageId <p/>
     *
     * @param clientId
     * @param subscriptionName
     * @param maxReturned
     * @param listener
     * @throws Exception
     */
    @Override
    public void recoverNextMessages(String clientId, String subscriptionName, int maxReturned, MessageRecoveryListener listener) throws Exception {
        // TODO implement org.apache.activemq.store.cassandra.CassandraTopicMessageStore.recoverNextMessages

    }

    /**
     * A hint to the Store to reset any batching state for a durable subscriber
     *
     * @param clientId
     * @param subscriptionName
     */
    @Override
    public void resetBatching(String clientId, String subscriptionName) {
        // TODO implement org.apache.activemq.store.cassandra.CassandraTopicMessageStore.resetBatching

    }

    /**
     * Get the number of messages ready to deliver from the store to a durable
     * subscriber
     *
     * @param clientId
     * @param subscriberName
     * @return the outstanding message count
     * @throws java.io.IOException
     */
    @Override
    public int getMessageCount(String clientId, String subscriberName) throws IOException {
        // TODO implement org.apache.activemq.store.cassandra.CassandraTopicMessageStore.getMessageCount
        return 0;
    }

    /**
     * Finds the subscriber entry for the given consumer info
     *
     * @param clientId
     * @param subscriptionName
     * @return the SubscriptionInfo
     * @throws java.io.IOException
     */
    @Override
    public SubscriptionInfo lookupSubscription(String clientId, String subscriptionName) throws IOException {
        // TODO implement org.apache.activemq.store.cassandra.CassandraTopicMessageStore.lookupSubscription
        return null;
    }

    /**
     * Lists all the durable subscriptions for a given destination.
     *
     * @return an array SubscriptionInfos
     * @throws java.io.IOException
     */
    @Override
    public SubscriptionInfo[] getAllSubscriptions() throws IOException {
        // TODO implement org.apache.activemq.store.cassandra.CassandraTopicMessageStore.getAllSubscriptions
        return new SubscriptionInfo[0];
    }

    /**
     * Inserts the subscriber info due to a subscription change <p/> If this is
     * a new subscription and the retroactive is false, then the last message
     * sent to the topic should be set as the last message acknowledged by they
     * new subscription. Otherwise, if retroactive is true, then create the
     * subscription without it having an acknowledged message so that on
     * recovery, all message recorded for the topic get replayed.
     *
     * @param clientId
     * @param subscriptionName
     * @param selector
     * @param retroactive
     * @throws java.io.IOException
     */
    @Override
    public void addSubsciption(SubscriptionInfo subscriptionInfo, boolean retroactive) throws IOException {
        // TODO implement org.apache.activemq.store.cassandra.CassandraTopicMessageStore.addSubsciption

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
        // TODO implement org.apache.activemq.store.cassandra.CassandraTopicMessageStore.addMessage

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
        // TODO implement org.apache.activemq.store.cassandra.CassandraTopicMessageStore.addMessage

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
        // TODO implement org.apache.activemq.store.cassandra.CassandraTopicMessageStore.asyncAddQueueMessage
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
        // TODO implement org.apache.activemq.store.cassandra.CassandraTopicMessageStore.asyncAddQueueMessage
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
        // TODO implement org.apache.activemq.store.cassandra.CassandraTopicMessageStore.asyncAddTopicMessage
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
        // TODO implement org.apache.activemq.store.cassandra.CassandraTopicMessageStore.asyncAddTopicMessage
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
        // TODO implement org.apache.activemq.store.cassandra.CassandraTopicMessageStore.getMessage
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
        // TODO implement org.apache.activemq.store.cassandra.CassandraTopicMessageStore.removeMessage

    }

    @Override
    public void removeAsyncMessage(ConnectionContext context, MessageAck ack) throws IOException {
        // TODO implement org.apache.activemq.store.cassandra.CassandraTopicMessageStore.removeAsyncMessage

    }

    /**
     * Removes all the messages from the message store.
     *
     * @param context
     * @throws java.io.IOException
     */
    @Override
    public void removeAllMessages(ConnectionContext context) throws IOException {
        // TODO implement org.apache.activemq.store.cassandra.CassandraTopicMessageStore.removeAllMessages

    }

    /**
     * Recover any messages to be delivered.
     *
     * @param container
     * @throws Exception
     */
    @Override
    public void recover(MessageRecoveryListener container) throws Exception {
        // TODO implement org.apache.activemq.store.cassandra.CassandraTopicMessageStore.recover

    }

    /**
     * The destination that the message store is holding messages for.
     *
     * @return the destination
     */
    @Override
    public ActiveMQDestination getDestination() {
        // TODO implement org.apache.activemq.store.cassandra.CassandraTopicMessageStore.getDestination
        return null;
    }

    /**
     * @param memoeyUSage The SystemUsage that is controlling the
     *                    destination's memory usage.
     */
    @Override
    public void setMemoryUsage(MemoryUsage memoeyUSage) {
        // TODO implement org.apache.activemq.store.cassandra.CassandraTopicMessageStore.setMemoryUsage

    }

    /**
     * @return the number of messages ready to deliver
     * @throws java.io.IOException
     */
    @Override
    public int getMessageCount() throws IOException {
        // TODO implement org.apache.activemq.store.cassandra.CassandraTopicMessageStore.getMessageCount
        return 0;
    }

    /**
     * A hint to the Store to reset any batching state for the Destination
     */
    @Override
    public void resetBatching() {
        // TODO implement org.apache.activemq.store.cassandra.CassandraTopicMessageStore.resetBatching

    }

    @Override
    public void recoverNextMessages(int maxReturned, MessageRecoveryListener listener) throws Exception {
        // TODO implement org.apache.activemq.store.cassandra.CassandraTopicMessageStore.recoverNextMessages

    }

    @Override
    public void dispose(ConnectionContext context) {
        // TODO implement org.apache.activemq.store.cassandra.CassandraTopicMessageStore.dispose

    }

    /**
     * allow caching cursors to set the current batch offset when cache is exhausted
     *
     * @param messageId
     * @throws Exception
     */
    @Override
    public void setBatch(MessageId messageId) throws Exception {
        // TODO implement org.apache.activemq.store.cassandra.CassandraTopicMessageStore.setBatch

    }

    /**
     * flag to indicate if the store is empty
     *
     * @return true if the message count is 0
     * @throws Exception
     */
    @Override
    public boolean isEmpty() throws Exception {
        // TODO implement org.apache.activemq.store.cassandra.CassandraTopicMessageStore.isEmpty
        return false;
    }

    /**
     * A hint to the store to try recover messages according to priority
     *
     * @param prioritizedMessages
     */
    @Override
    public void setPrioritizedMessages(boolean prioritizedMessages) {
        // TODO implement org.apache.activemq.store.cassandra.CassandraTopicMessageStore.setPrioritizedMessages

    }

    /**
     * @return true if store is trying to recover messages according to priority
     */
    @Override
    public boolean isPrioritizedMessages() {
        // TODO implement org.apache.activemq.store.cassandra.CassandraTopicMessageStore.isPrioritizedMessages
        return false;
    }

    @Override
    public void start() throws Exception {
        // TODO implement org.apache.activemq.store.cassandra.CassandraTopicMessageStore.start

    }

    @Override
    public void stop() throws Exception {
        // TODO implement org.apache.activemq.store.cassandra.CassandraTopicMessageStore.stop

    }
}
