package com.sfarc.monitor.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * @author Tharindu Aththnayake
 * @since 04/02/2021 02:53 AM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "sensor_data")
public class SensorData
{
	@Id
	private String sensorId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime date;
	private String value;
}
