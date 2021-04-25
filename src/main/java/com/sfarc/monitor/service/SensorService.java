package com.sfarc.monitor.service;

import com.sfarc.monitor.config.InMemoryHashTypes;
import com.sfarc.monitor.entity.Sensor;
import com.sfarc.monitor.repository.SensorRepository;
import com.sfarc.monitor.web.dto.SensorDto;
import com.sfarc.monitor.web.mappers.SensorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author madhuwantha
 * created on 4/20/2021
 */
@Service
public class SensorService {

    @Autowired
    CacheService<List<String>> cacheService;

    @Autowired
    SensorRepository sensorRepository;

    @Autowired
    SensorMapper sensorMapper;

    public List<String> getCurrentSubscriberIds(String sensorId){
//        return cacheService.get(InMemoryHashTypes.SENSOR_LISTENERS, sensorId);
        return List.of("client","bbb","ccc");
    }

    public SensorDto save(SensorDto sensorDto){
        return sensorMapper.sensorToSensorDto(sensorRepository.save(sensorMapper.sensorDtoToSensor(sensorDto)));
    }

    public List<SensorDto> getSensors(){
        return sensorRepository.findAll()
                .stream()
                .map(sensorMapper::sensorToSensorDto)
                .collect(Collectors.toList());
    }

    public SensorDto addSensor(SensorDto sensorDto){
        Sensor savedSensor = sensorRepository.save( sensorMapper.sensorDtoToSensor( sensorDto ) );
        return sensorMapper.sensorToSensorDto( savedSensor );
    }


}
