/*
 * This file is part of TI. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of TI,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.ti.sink.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.function.Consumer;

/**
 * Factory for Apache Kafka based sinks.
 *
 * @see <a href="https://kafka.apache.org/">Apache Kafka website</a>
 * @see <a href="http://kafka.apache.org/documentation.html#producerconfigs">Producer configuration</a>
 */
public final class KafkaSinks {

    /**
     * @param bootStrapServers A list of bootstrap servers to use. Must be in the form 'host1:port1,host2:port2,...'.
     * @param topicName        The topic name to use.
     * @return A consumer that writes into a Kafka queue.
     */
    public static Consumer<String> kafka(final String bootStrapServers, final String topicName) {
        final var props = new Properties();
        props.put("bootstrap.servers", bootStrapServers); //$NON-NLS-1$
        return kafka(props, topicName);
    }

    /**
     * @param props     The producer properties to use.
     * @param topicName The topic name to use.
     * @return A consumer that writes into a Kafka queue.
     */
    public static Consumer<String> kafka(final Properties props, final String topicName) {
        return message -> {
            try (final var producer = new KafkaProducer<>(props)) {
                producer.send(new ProducerRecord<>(topicName, message));
            }
        };
    }

    /**
     * @param topicName The topic name to use.
     * @param producer  The producer to use.
     * @return A consumer that writes into a Kafka queue.
     */
    public static Consumer<String> kafka(final String topicName, final Producer<String, String> producer) {
        return message -> producer.send(new ProducerRecord<>(topicName, message));
    }

}
