package com.sfarc.monitor.model;

public class Sensor {
    private String sensorReading;

    public Sensor() {
    }

    public Sensor(String sensorReading) {
        this.sensorReading = sensorReading;
    }

    public String getSensorReading() {
        return sensorReading;
    }
}
