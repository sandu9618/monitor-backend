package com.sfarc.monitor.config;

/**
 * @author madhuwantha
 * created on 4/19/2021
 */
public enum InMemoryHashTypes {
    SENSOR_LISTENERS("sensor_listener");

    private String state;
    private InMemoryHashTypes(String state) {
        state = "c";
    }
}
