package org.quark.microapidemo.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaProducerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String servers;
    @Value("${spring.kafka.key.deserializer}")
    private String keySerializer;
    @Value("${spring.kafka.value.deserializer}")
    private String valueSerializer;

    @Value("${spring.kafka.retries}")
    private int retries;
    @Value("${spring.kafka.batch.size}")
    private int batchSize;
    @Value("${spring.kafka.linger}")
    private int linger;
    @Value("${spring.kafka.compression.type}")
    private String compressionType;
    @Value("${spring.kafka.acks}")
    private int acks;

    public Map<String, Object> producerConfigs() {

        Map<String, Object> propsMap = new HashMap<>();

        propsMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        propsMap.put(ProducerConfig.RETRIES_CONFIG, retries);
        propsMap.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSize);
        propsMap.put(ProducerConfig.LINGER_MS_CONFIG, linger);
        propsMap.put(ProducerConfig.ACKS_CONFIG, acks);
        propsMap.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, compressionType);

        try {
            propsMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, Class.forName(keySerializer));
            propsMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, Class.forName(valueSerializer));
        } catch (ClassNotFoundException e) {
            propsMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
            propsMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        }

        return propsMap;
    }

    public ProducerFactory<String, String> producerFactory() {

        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {

        return new KafkaTemplate<String, String>(producerFactory());
    }
}
