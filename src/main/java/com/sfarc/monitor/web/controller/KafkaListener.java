package com.sfarc.monitor.web.controller;


import com.sfarc.monitor.config.KafkaConstants;
import com.sfarc.monitor.web.dto.SensorDataDto;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Sanduni Pavithra
 * Created on 4/12/2021
 */

@Component
public class KafkaListener {
    private static final Logger LOG = LoggerFactory.getLogger(KafkaListener.class);

    @Autowired
    private SimpMessagingTemplate webSocket;

    @org.springframework.kafka.annotation.KafkaListener(topics = KafkaConstants.KAFKA_TOPIC)
    public void processMessage(ConsumerRecord<String, SensorDataDto> cr, @Payload SensorDataDto content) {
        LOG.info("Received content from Kafka: {}", content);

        this.webSocket.convertAndSend(KafkaConstants.WEBSOCKET_DESTINATION, content.getValue());
    }
}
