package com.sfarc.monitor.service;

import com.sfarc.monitor.component.converter.NotificationModelConverter;
import com.sfarc.monitor.component.logic.Logic;
import com.sfarc.monitor.component.logic.LogicFactory;
import com.sfarc.monitor.component.logic.LogicFinder;
import com.sfarc.monitor.component.model.NotificationModel;
import com.sfarc.monitor.component.notifiers.EmailNotifier;
import com.sfarc.monitor.component.notifiers.SMSNotifier;
import com.sfarc.monitor.component.notifiers.VoiceCallNotifier;
import com.sfarc.monitor.entity.Sensor;
import com.sfarc.monitor.entity.SensorData;
import com.sfarc.monitor.entity.User;
import com.sfarc.monitor.repository.AlertRepository;
import com.sfarc.monitor.repository.SensorRepository;
import com.sfarc.monitor.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.sfarc.monitor.web.exception.FailedProcessingSensorDataException;


import java.util.Optional;

/**
 * @author Tharindu Aththnayake
 * @since 04/04/2021 01:12 AM
 */
@Service
@Slf4j
public class AlertService
{
	@Autowired
	private LogicFactory logicFactory;

	@Autowired
	private AlertRepository alertRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SensorRepository sensorRepository;

	@Autowired
	private NotificationModelConverter notificationModelConverter;

	@Autowired
	private EmailNotifier emailNotifier;

	@Autowired
	private SMSNotifier smsNotifier;

	@Autowired
	private VoiceCallNotifier voiceCallNotifier;



	/**
	 * Checking sensor data with defined logic s
	 *
	 * @param sensorData The Sensor Data
	 * @return Accepted Response Entity
	 */
	public ResponseEntity checkSensorData( SensorData sensorData )
	{
		Logic logic = logicFactory.findLogic( LogicFinder.findLogicName( sensorData.getSensorId() ) );

		try
		{
			if (logic.check( sensorData.getValue() ))
			{
				Optional <User> optionalUser = userRepository.findUserByUserSensors( sensorData.getSensorId() );
				Optional< Sensor > optionalSensor = sensorRepository.findSensorBySensorId( sensorData.getSensorId() );

				NotificationModel notificationModel = notificationModelConverter.convert( optionalUser.get(), optionalSensor.get(), sensorData  );
				emailNotifier.notifyUser( notificationModel );
				voiceCallNotifier.notifyUser( notificationModel );
				smsNotifier.notifyUser( notificationModel );

				alertRepository.save( sensorData );
			}

			return ResponseEntity
					.status( HttpStatus.ACCEPTED )
					.build();
		}
		catch( Exception ex )
		{
			ex.printStackTrace();
			return ResponseEntity
					.status( HttpStatus.INTERNAL_SERVER_ERROR )
					.build();
		}

	}
}
