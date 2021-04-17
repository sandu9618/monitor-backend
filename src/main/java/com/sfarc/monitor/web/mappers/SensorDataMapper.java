package com.sfarc.monitor.web.mappers;

import com.sfarc.monitor.web.dto.SensorDataDto;
import com.sfarc.monitor.entity.SensorData;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface SensorDataMapper {
    SensorData sensorDtaDtoToSensorData(SensorDataDto sensorDataDto);

    SensorDataDto sensorDataToSensorDataDto(SensorData sensorData);
}
