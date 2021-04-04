package com.sfarc.monitor.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document( collection = "sensors" )
public class Sensor
{
	private String sensorId;
	private String sensorName;
}
