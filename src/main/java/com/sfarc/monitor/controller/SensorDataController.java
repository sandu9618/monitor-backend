package com.sfarc.monitor.controller;

import com.sfarc.monitor.model.SensorData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tharindu Aththnayake
 * @since 04/02/2021 02:53 AM
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/sensor")
public class SensorDataController
{
	@PostMapping
	public ResponseEntity collectSensorData( @RequestBody SensorData sensorData ){
		return ResponseEntity
				.status( HttpStatus.ACCEPTED )
				.build();
	}
}
