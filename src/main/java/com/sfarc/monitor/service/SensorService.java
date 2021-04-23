package com.sfarc.monitor.service;

import com.sfarc.monitor.config.InMemoryHashTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author madhuwantha
 * created on 4/20/2021
 */
@Service
public class SensorService {

    @Autowired
    CacheService<List<String>> cacheService;

    public List<String> getCurrentSubscriberIds(String sensorId){
        return cacheService.get(InMemoryHashTypes.SENSOR_LISTENERS, sensorId);
    }
}
