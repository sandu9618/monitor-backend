package com.sfarc.monitor.web.controller;

import com.sfarc.monitor.entity.SensorData;
import com.sfarc.monitor.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/sensor")
public class SensorDataController
{
	@Autowired
	private AlertService alertService;

	@PostMapping
	public ResponseEntity collectSensorData( @RequestBody SensorData sensorData ){
		return alertService.checkSensorData( sensorData );
	}
}
