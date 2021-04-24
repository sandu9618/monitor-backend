package com.sfarc.monitor.web.controller;

import com.sfarc.monitor.service.SensorService;
import com.sfarc.monitor.service.UserService;
import com.sfarc.monitor.web.dto.UserDto;
import com.sfarc.monitor.web.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

/**
 * @author madhuwantha
 * created on 4/19/2021
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/")
    public String test(){
        return "working";
    }

    @Autowired
    UserService userService;

    @Autowired
    SensorService sensorService;

    @GetMapping("/all")
    ResponseEntity<ApiResponse> getAll(){
        ApiResponse apiResponse = ApiResponse
                .builder()
                .status(true)
                .body(userService.getAllUsers())
                .build();

        return ResponseEntity.ok(apiResponse);
    }


    @PostMapping("/save")
    ResponseEntity<ApiResponse> save(@RequestBody UserDto userDto) {
        System.out.println(userDto.toString()+ "save");
        ApiResponse apiResponse = ApiResponse
                .builder()
                .status(true)
                .body(userService.save(userDto))
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/{id}/sensor/{sensorId}")
    ResponseEntity<ApiResponse> addSensorToUser(@PathVariable String id, @PathVariable String sensorId){
        ApiResponse apiResponse = ApiResponse
                .builder()
                .status(true)
                .body(userService.addSensor(id, sensorId))
                .build();

        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping("/{id}/sensors")
    ResponseEntity<ApiResponse> getUserSensors(@PathVariable String id)
            throws EntityNotFoundException
    {
        ApiResponse apiResponse = ApiResponse
                .builder()
                .status(true)
                .body(userService.getOwnSensors(id))
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("{userId}/select/sensor/{sensorId}")
    ResponseEntity<ApiResponse> selectSensor(@PathVariable String userId, @PathVariable String sensorId){
        userService.selectSensor(userId, sensorId);
        ApiResponse apiResponse = ApiResponse
                .builder()
                .status(true)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

}
