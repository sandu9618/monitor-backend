package com.sfarc.monitor.web.controller;

import com.sfarc.monitor.entity.Alert;
import com.sfarc.monitor.service.AlertService;
import com.sfarc.monitor.web.dto.UserDto;
import com.sfarc.monitor.web.response.ApiResponse;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * @author Sanduni Pavithra
 * Created on 4/25/2021
 */

@RestController
@RequestMapping("/alert")
public class AlertController {

    @Autowired
    AlertService alertService;

    @GetMapping("/user/{user}")
    ResponseEntity<ApiResponse> getAlerts(@PathVariable("user") String user) {
        ApiResponse apiResponse = ApiResponse
                .builder()
                .status(true)
                .body(alertService.getAlerts(user))
                .build();

        return ResponseEntity.ok(apiResponse);
    }

}
