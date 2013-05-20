package org.apache.activemq.store.cassandra;

import com.datastax.driver.core.Session;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;

/**
 * User: smeagol
 * Date: 18.05.13
 * Time: 11:36
 */
public class CassandraStorage {
    private final Session session;
    public Queues queues = new Queues();
    public Topics topics = new Topics();

    public CassandraStorage(Session session) {
        this.session = session;
    }

    public class Queues {

        public Iterable<String> list() {
            //TODO implement org.apache.activemq.store.cassandra.CassandraStorage.Queues.list
            return null;
        }

        public void save(ActiveMQQueue destination) {
            // TODO implement org.apache.activemq.store.cassandra.CassandraStorage.Queues.save
        }

        public void remove(ActiveMQQueue destination) {
            // TODO implement org.apache.activemq.store.cassandra.CassandraStorage.Queues.remove
        }
    }

    public class Topics {

        public Iterable<String> list() {
            //TODO implement org.apache.activemq.store.cassandra.CassandraStorage.Topics.list
            return null;
        }

        public void save(ActiveMQTopic destination) {
            // TODO implement org.apache.activemq.store.cassandra.CassandraStorage.Topics.save
        }

        public void remove(ActiveMQTopic destination) {
            // TODO implement org.apache.activemq.store.cassandra.CassandraStorage.Topics.remove
        }
    }
}
