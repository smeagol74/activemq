package org.apache.activemq.store.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import org.apache.activemq.broker.Locker;
import org.apache.activemq.store.PersistenceAdapter;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: smeagol
 * Date: 17.05.13
 * Time: 16:03
 * To change this template use File | Settings | File Templates.
 */
public class CassandraLocker implements Locker {

    public CassandraLocker(Session session) {
        //TODO implement org.apache.activemq.store.cassandra.CassandraLocker.CassandraLocker
    }

    @Override
    public boolean keepAlive() throws IOException {
        //TODO implement org.apache.activemq.store.cassandra.CassandraLocker.keepAlive
        return false;
    }

    @Override
    public void setLockAcquireSleepInterval(long lockAcquireSleepInterval) {
        //TODO implement org.apache.activemq.store.cassandra.CassandraLocker.setLockAcquireSleepInterval

    }

    @Override
    public void setName(String name) {
        //TODO implement org.apache.activemq.store.cassandra.CassandraLocker.setName

    }

    @Override
    public void setFailIfLocked(boolean failIfLocked) {
        //TODO implement org.apache.activemq.store.cassandra.CassandraLocker.setFailIfLocked

    }

    @Override
    public void configure(PersistenceAdapter persistenceAdapter) throws IOException {
        //TODO implement org.apache.activemq.store.cassandra.CassandraLocker.configure

    }

    @Override
    public void start() throws Exception {
        //TODO implement org.apache.activemq.store.cassandra.CassandraLocker.start

    }

    @Override
    public void stop() throws Exception {
        //TODO implement org.apache.activemq.store.cassandra.CassandraLocker.stop

    }
}
