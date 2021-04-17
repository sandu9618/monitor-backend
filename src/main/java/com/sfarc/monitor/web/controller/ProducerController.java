package com.sfarc.monitor.web.controller;

import com.sfarc.monitor.constant.KafkaConstants;
import com.sfarc.monitor.entity.SensorData;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.ui.Model;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * @author Sanduni Pavithra
 * Created on 4/12/2021
 * Mock sensor data
 * Use: GET /kafka/sensor/{amount}
 */

@RestController
@RequestMapping(value = "/api/kafka")
public class ProducerController {

    private static final Logger LOG = LoggerFactory.getLogger(ProducerController.class);

    private final ProducerCallback producerCallback = new ProducerCallback();

    @Autowired
    private KafkaTemplate<String, SensorData> kafkaTemplate;

    @RequestMapping(value = "/sensor/{amount}", method = RequestMethod.GET)
    public void generateMessages(@PathVariable Integer amount) {

        int[] array = new Random().ints(10, 0, 100).toArray();

        IntStream stream = Arrays.stream(array);
        stream.peek(val -> this.waitFor(1))
                .mapToObj(val -> new SensorData(Collections.singletonList(array).indexOf(val), val+""))
                .forEach(this::sendToKafka);
    }

    private void sendToKafka(SensorData sensorData) {
        this.kafkaTemplate
                .send(KafkaConstants.KAFKA_TOPIC, UUID.randomUUID().toString(), sensorData)
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
