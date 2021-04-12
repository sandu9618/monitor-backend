package com.sfarc.monitor.repository;

import com.sfarc.monitor.entity.SensorData;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Tharindu Aththnayake
 * @since 04/04/2021 01:43 AM
 */
public interface AlertRepository extends MongoRepository< SensorData, String >
{
}
