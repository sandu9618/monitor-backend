package com.sfarc.monitor.web.mappers;

import com.sfarc.monitor.web.dto.SensorDataDto;
import com.sfarc.monitor.entity.Alert;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface SensorDataMapper {
    Alert sensorDtaDtoToSensorData( SensorDataDto sensorDataDto);

    SensorDataDto sensorDataToSensorDataDto( Alert alert );
}
