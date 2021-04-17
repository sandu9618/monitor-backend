package com.sfarc.monitor.service;

import com.sfarc.monitor.config.Constants;
import com.sfarc.monitor.web.dto.SensorDataDto;
import com.sfarc.monitor.entity.SensorData;
import com.sfarc.monitor.web.mappers.SensorDataMapper;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.UUID;
import java.util.concurrent.*;

/**
 * @author Sanduni Pavithra
 * Created on 4/18/2021
 */

@Service
public class ClientService {

    @Autowired
    private SensorDataMapper sensorDataMapper;

    @Autowired
    private SimpMessagingTemplate webSocket;

    public void sendToClient(SensorDataDto sensorDataDto) {
        SensorData sensorData = sensorDataMapper.sensorDtaDtoToSensorData(sensorDataDto);
        this.webSocket.convertAndSend(Constants.WEBSOCKET_DESTINATION, sensorData.getValue());

    }
}
