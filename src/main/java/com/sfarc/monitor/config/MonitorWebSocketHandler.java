package com.sfarc.monitor.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sfarc.monitor.model.SensorData;
import com.sfarc.monitor.services.KafkaService;
import org.apache.logging.log4j.message.Message;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

public class MonitorWebSocketHandler implements WebSocketHandler {

    private static final ObjectMapper json = new ObjectMapper();

    private KafkaService kafkaService;

    @Override
    public Mono<Void> handle(WebSocketSession webSocketSession) {
        return webSocketSession.send(kafkaService.getTestTopicFlux()
                .map(record -> {
                    SensorData sensorData = new SensorData("Testing", record.value());

                    try {
                        return json.writeValueAsString(sensorData);
                    }catch (JsonProcessingException e){
                        return "Error while passing sensor data";
                    }
                })
                .map(webSocketSession::textMessage))
                .and(webSocketSession.receive()
                        .map(WebSocketMessage::getPayloadAsText).log()
        );
    }
}
