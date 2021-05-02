package com.sfarc.monitor.config;

import com.sfarc.monitor.web.dto.SensorDataDto;
import com.sfarc.monitor.web.dto.SensorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Random;

/**
 * @author madhuwantha
 * created on 4/25/2021
 */

@Slf4j
//@EnableScheduling
@Component
public class DataSource {

    RestTemplate restTemplate = new RestTemplate();

    final String baseUrl = "http://localhost:8095/sensor";

    Random rand = new Random();

    @Scheduled(fixedRate = 500)
    public void greeting() {
        SensorDataDto sensorDataDto = SensorDataDto
                .builder()
                .value(rand.nextInt(10) + 1+"C")
                .date(LocalDateTime.now())
                .sensorId("TE02")
                .build();

        ResponseEntity<String> result = restTemplate.postForEntity(baseUrl, sensorDataDto, String.class);


        log.info("===== {}", result);
    }
}
