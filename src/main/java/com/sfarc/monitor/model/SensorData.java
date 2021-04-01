package com.sfarc.monitor.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SensorData {
    private String type;
    private String value;
}
