package com.sfarc.monitor.web.controller;

import com.sfarc.monitor.service.ClientService;
import com.sfarc.monitor.web.dto.SensorDataDto;
import com.sfarc.monitor.service.AlertService;
import com.sfarc.monitor.web.exception.BadRequestException;
import com.sfarc.monitor.web.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

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

	@Autowired
	private ClientService clientService;

	@PostMapping
	public ResponseEntity<ApiResponse>  collectSensorData( @RequestBody SensorDataDto sensorDataDto )
			throws BadRequestException, IOException {
		System.out.println(sensorDataDto);
		 clientService.sendToClient(sensorDataDto);
		alertService.checkSensorData( sensorDataDto );

		ApiResponse apiResponse = ApiResponse
				.builder()
				.status(true)
				.message("Successfully processed the data")
				.build();
		return ResponseEntity.ok(apiResponse);
	}
}
