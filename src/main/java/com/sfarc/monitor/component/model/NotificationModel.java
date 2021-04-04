package com.sfarc.monitor.component.model;

import com.sfarc.monitor.entity.Sensor;
import com.sfarc.monitor.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationModel
{
	private User user;
	private Sensor sensor;
	private String criticalValue;
	private String time;

	public void setTime( LocalDateTime localDateTime )
	{
		this.time = localDateTime.toString();
	}
}
