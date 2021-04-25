package com.sfarc.monitor.web.controller;

import com.sfarc.monitor.component.notifiers.NotifierType;
import com.sfarc.monitor.service.SensorService;
import com.sfarc.monitor.service.UserService;
import com.sfarc.monitor.web.dto.UserDto;
import com.sfarc.monitor.web.payoad.SelectSensorRequest;
import com.sfarc.monitor.web.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * @author madhuwantha
 * created on 4/19/2021
 */

@Slf4j
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

    @GetMapping
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

    @PostMapping("/select-sensor")
    ResponseEntity<ApiResponse> selectSensor(@RequestBody SelectSensorRequest selectSensorRequest){
        log.info("calling selectSensor controller ");
        userService.selectSensor(selectSensorRequest.getUserId(), selectSensorRequest.getSensorId(), selectSensorRequest.getPreviousSensorId());
        ApiResponse apiResponse = ApiResponse
                .builder()
                .status(true)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("{userId}/notifiers")
    ResponseEntity<ApiResponse> getUserNotifiers(@PathVariable String userId )
    {
        ApiResponse apiResponse = ApiResponse
                .builder()
                .status(true)
                .body( userService.getUserNotifierTypes( userId ) )
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("{userId}/notifiers")
    ResponseEntity<ApiResponse> updateUserNotifiers(@PathVariable String userId, @RequestBody List<NotifierType> notifierTypes )
    {
        ApiResponse apiResponse = ApiResponse
                .builder()
                .status(true)
                .body( userService.updateUserNotificationTypes( userId, notifierTypes ) )
                .build();

        return ResponseEntity.ok(apiResponse);
    }

}
