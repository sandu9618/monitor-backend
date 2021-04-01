package com.sfarc.monitor.service;

import com.sfarc.monitor.model.SensorData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Tharindu Aththnayake
 * @since 04/02/2021 03:09 AM
 */
@Service
@Slf4j
public class SensorDataService
{
	public ResponseEntity collectSensorData(  SensorData sensorData )
	{
		return ResponseEntity
				.status( HttpStatus.ACCEPTED )
				.build();
	}
}
