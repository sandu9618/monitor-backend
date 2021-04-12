package com.sfarc.monitor.component.converter;

import com.sfarc.monitor.component.model.NotificationModel;
import com.sfarc.monitor.entity.Sensor;
import com.sfarc.monitor.entity.SensorData;
import com.sfarc.monitor.entity.User;
import org.springframework.stereotype.Component;

@Component
public class NotificationModelConverter
{
	public NotificationModel convert( User user, Sensor sensor, SensorData sensorData )
	{
		NotificationModel notificationModel = new NotificationModel();
		notificationModel.setUser( user );
		notificationModel.setSensor( sensor );
		notificationModel.setCriticalValue( sensorData.getValue() );
		notificationModel.setTime( sensorData.getDate() );

		return notificationModel;
	}
}
