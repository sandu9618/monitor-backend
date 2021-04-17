package com.sfarc.monitor.service.impl;

import com.sfarc.monitor.service.KafkaService;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import reactor.core.publisher.Flux;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.receiver.ReceiverRecord;

import java.io.IOException;
import java.util.Collections;
import java.util.Properties;

public class kafkaServiceImpl implements KafkaService {

    private Flux<ReceiverRecord<String, String>> testTopicStream;

    public kafkaServiceImpl() throws IOException {
        Properties kafkaProperties = PropertiesLoaderUtils.loadAllProperties("ccloud.properties");

        kafkaProperties.put(ConsumerConfig.CLIENT_ID_CONFIG, "reactive-consumer");
        kafkaProperties.put(ConsumerConfig.GROUP_ID_CONFIG, "sample-group");
        kafkaProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        kafkaProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        kafkaProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        ReceiverOptions<String, String> receiverOptions = ReceiverOptions.create(kafkaProperties);

        testTopicStream = createTopicCache(receiverOptions, "testTopic");
    }

    private <T, G> Flux<ReceiverRecord <T, G>> createTopicCache(ReceiverOptions <T, G> receiverOptions, String testTopic) {
        ReceiverOptions<T, G> options = receiverOptions.subscription(Collections.singleton(testTopic));

        return KafkaReceiver.create(options).receive().cache();
    }

    @Override
    public Flux<ReceiverRecord<String, String>> getTestTopicFlux() {
        return testTopicStream;
    }
}
