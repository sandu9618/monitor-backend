package com.sfarc.monitor.web.controller;

import com.sfarc.monitor.entity.SensorData;
import com.sfarc.monitor.service.AlertService;
import com.sfarc.monitor.web.exception.BadRequestException;
import com.sfarc.monitor.web.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
	public ResponseEntity<ApiResponse> collectSensorData(@Validated  @RequestBody SensorData sensorData )
			throws BadRequestException
	{
		alertService.checkSensorData( sensorData );
		ApiResponse apiResponse = ApiResponse
				.builder()
				.status(true)
				.message("Successfully processed the data")
				.build();
		return ResponseEntity.ok(apiResponse);
	}
}
