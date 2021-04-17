package com.sfarc.monitor.service;

import reactor.core.publisher.Flux;
import reactor.kafka.receiver.ReceiverRecord;

public interface KafkaService {
    public Flux<ReceiverRecord<String, String>> getTestTopicFlux();
}
