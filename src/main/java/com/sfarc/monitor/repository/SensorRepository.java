package com.sfarc.monitor.repository;

import com.sfarc.monitor.entity.Sensor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SensorRepository extends MongoRepository< Sensor, String >
{
	Optional<Sensor> findSensorBySensorId(String sensorId);
}
