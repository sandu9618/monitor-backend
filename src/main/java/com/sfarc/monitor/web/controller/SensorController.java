package com.sfarc.monitor.web.controller;

import com.sfarc.monitor.component.notifiers.NotifierType;
import com.sfarc.monitor.service.SensorService;
import com.sfarc.monitor.web.dto.SensorDto;
import com.sfarc.monitor.web.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

	@GetMapping("/{id}")
	ResponseEntity<ApiResponse> get(@PathVariable String id){
		ApiResponse apiResponse = ApiResponse
				.builder()
				.status(true)
				.body( sensorService.get(id) )
				.build();
		return ResponseEntity.ok(apiResponse);
	}

	@GetMapping
	ResponseEntity<ApiResponse> saveSensors(){
		ApiResponse apiResponse = ApiResponse
				.builder()
				.status(true)
				.body( sensorService.getSensors() )
				.build();
		return ResponseEntity.ok(apiResponse);
	}

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
