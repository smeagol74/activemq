package org.apache.activemq.store.cassandra;

import com.datastax.driver.core.Session;
import org.apache.activemq.command.TransactionId;
import org.apache.activemq.store.TransactionRecoveryListener;
import org.apache.activemq.store.TransactionStore;

import java.io.IOException;

/**
 * User: smeagol
 * Date: 18.05.13
 * Time: 16:12
 */
public class CassandraTransactionsStore implements TransactionStore {
    public CassandraTransactionsStore(Session session) {
        // TODO implement org.apache.activemq.store.cassandra.CassandraTransactionsStore.CassandraTransactionsStore

    }

    @Override
    public void prepare(TransactionId txid) throws IOException {
        // TODO implement org.apache.activemq.store.cassandra.CassandraTransactionsStore.prepare

    }

    @Override
    public void commit(TransactionId txid, boolean wasPrepared, Runnable preCommit, Runnable postCommit) throws IOException {
        // TODO implement org.apache.activemq.store.cassandra.CassandraTransactionsStore.commit

    }

    @Override
    public void rollback(TransactionId txid) throws IOException {
        // TODO implement org.apache.activemq.store.cassandra.CassandraTransactionsStore.rollback

    }

    @Override
    public void recover(TransactionRecoveryListener listener) throws IOException {
        // TODO implement org.apache.activemq.store.cassandra.CassandraTransactionsStore.recover

    }

    @Override
    public void start() throws Exception {
        // TODO implement org.apache.activemq.store.cassandra.CassandraTransactionsStore.start

    }

    @Override
    public void stop() throws Exception {
        // TODO implement org.apache.activemq.store.cassandra.CassandraTransactionsStore.stop

    }
}
