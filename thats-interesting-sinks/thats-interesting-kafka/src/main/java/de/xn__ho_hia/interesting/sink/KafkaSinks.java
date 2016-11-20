package de.xn__ho_hia.interesting.sink;

import java.util.Properties;
import java.util.function.Consumer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * Factory for Apache Kafka based sinks.
 *
 * @see <a href="https://kafka.apache.org/">Apache Kafka website</a>
 */
public final class KafkaSinks {

    /**
     * @return A consumer that writes into a Kafka queue.
     */
    public static final Consumer<String> kafka(final String bootStrapServers, final String topicName) {
        final Properties props = new Properties();
        props.put("bootstrap.servers", bootStrapServers);

        return message -> {
            try (final Producer<String, String> producer = new KafkaProducer<>(props)) {
                producer.send(new ProducerRecord<>(topicName, message));
            }
        };
    }

    /**
     * @return A consumer that writes into a Kafka queue.
     */
    public static final Consumer<String> kafka(final String topicName, final Producer<String, String> producer) {
        return message -> producer.send(new ProducerRecord<>(topicName, message));
    }

}
