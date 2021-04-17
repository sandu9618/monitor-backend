package com.sfarc.monitor.service;

import com.sfarc.monitor.constant.KafkaConstants;
import com.sfarc.monitor.dto.SensorDataDto;
import com.sfarc.monitor.entity.SensorData;
import com.sfarc.monitor.web.mappers.SensorDataMapper;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.UUID;
import java.util.concurrent.*;

/**
 * @author Sanduni Pavithra
 * Created on 4/18/2021
 */

@Service
public class KafkaService {
    private static final Logger LOG = LoggerFactory.getLogger(KafkaService.class);

    private final ProducerCallback producerCallback = new ProducerCallback();

    @Autowired
    private SensorDataMapper sensorDataMapper;

    @Autowired
    private KafkaTemplate<String, SensorData> kafkaTemplate;

    public void sendToKafka(SensorDataDto sensorDataDto) {
        this.kafkaTemplate
                .send(KafkaConstants.KAFKA_TOPIC, UUID.randomUUID().toString(), sensorDataMapper.sensorDtaDtoToSensorData(sensorDataDto))
                .addCallback(this.producerCallback);
    }

    private void waitFor(int seconds) {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        Future<Void> future = scheduler.schedule(() -> null, seconds, TimeUnit.SECONDS);
        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    class ProducerCallback implements ListenableFutureCallback<SendResult<String, SensorData>> {
        @Override
        public void onSuccess(SendResult<String, SensorData> result) {
            RecordMetadata record = result.getRecordMetadata();
            LOG.info("Sending {} to topic {} - partition {}",
                    result.getProducerRecord().key(),
                    result.getProducerRecord().topic(),
                    record.partition());
        }

        @Override
        public void onFailure(Throwable ex) {
            LOG.error("Producer Failure", ex);
        }
    }
}
