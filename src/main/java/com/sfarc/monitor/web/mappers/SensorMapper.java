package com.sfarc.monitor.web.mappers;

import com.sfarc.monitor.entity.Sensor;
import com.sfarc.monitor.web.dto.SensorDto;
import org.mapstruct.Mapper;

/**
 * @author madhuwantha
 * created on 4/19/2021
 */

@Mapper(uses = {DateMapper.class})
public interface SensorMapper {
    Sensor sensorDtoToSensor(SensorDto sensorDto);
    SensorDto sensorToSensorDto(Sensor sensor);
}
