package wtf.metio.ti.sink;

import java.util.Properties;
import java.util.function.Consumer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * Factory for Apache Kafka based sinks.
 *
 * @see <a href="https://kafka.apache.org/">Apache Kafka website</a>
 * @see <a href="http://kafka.apache.org/documentation.html#producerconfigs">Producer configuration</a>
 */
public final class KafkaSinks {

    /**
     * @param bootStrapServers
     *            A list of bootstrap servers to use. Must be in the form 'host1:port1,host2:port2,...'.
     * @param topicName
     *            The topic name to use.
     * @return A consumer that writes into a Kafka queue.
     */
    public static final Consumer<String> kafka(final String bootStrapServers, final String topicName) {
        final Properties props = new Properties();
        props.put("bootstrap.servers", bootStrapServers); //$NON-NLS-1$
        return kafka(props, topicName);
    }

    /**
     * @param props
     *            The producer properties to use.
     * @param topicName
     *            The topic name to use.
     * @return A consumer that writes into a Kafka queue.
     */
    public static final Consumer<String> kafka(final Properties props, final String topicName) {
        return message -> {
            try (final Producer<String, String> producer = new KafkaProducer<>(props)) {
                producer.send(new ProducerRecord<>(topicName, message));
            }
        };
    }

    /**
     * @param topicName
     *            The topic name to use.
     * @param producer
     *            The producer to use.
     * @return A consumer that writes into a Kafka queue.
     */
    public static final Consumer<String> kafka(final String topicName, final Producer<String, String> producer) {
        return message -> producer.send(new ProducerRecord<>(topicName, message));
    }

}
