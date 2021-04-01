package com.sfarc.monitor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Tharindu Aththnayake
 * @since 04/02/2021 02:53 AM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SensorData
{
	private Integer sensorId;
	private LocalDateTime date;
	private String value;
}
