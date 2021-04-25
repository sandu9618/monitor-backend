package com.sfarc.monitor.web.controller;

import com.sfarc.monitor.component.notifiers.NotifierType;
import com.sfarc.monitor.service.SensorService;
import com.sfarc.monitor.web.dto.SensorDto;
import com.sfarc.monitor.web.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author madhuwantha
 * created on 4/25/2021
 */

@RestController
@RequestMapping("/sensors")
public class SensorController {

	@Autowired
	private SensorService sensorService;

	@PostMapping
	ResponseEntity<ApiResponse> saveSensors( @RequestBody SensorDto sensorDto )
	{
		ApiResponse apiResponse = ApiResponse
				.builder()
				.status(true)
				.body( sensorService.save( sensorDto ) )
				.build();

		return ResponseEntity.ok(apiResponse);
	}

}
