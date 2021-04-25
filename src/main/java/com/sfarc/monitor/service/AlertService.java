package com.sfarc.monitor.service;

import com.sfarc.monitor.component.converter.NotificationModelConverter;
import com.sfarc.monitor.component.logic.Logic;
import com.sfarc.monitor.component.logic.LogicFactory;
import com.sfarc.monitor.component.logic.LogicFinder;
import com.sfarc.monitor.component.model.NotificationModel;
import com.sfarc.monitor.component.notifiers.EmailNotifier;
import com.sfarc.monitor.component.notifiers.SMSNotifier;
import com.sfarc.monitor.component.notifiers.VoiceCallNotifier;
import com.sfarc.monitor.web.dto.SensorDataDto;
import com.sfarc.monitor.entity.Sensor;
import com.sfarc.monitor.entity.Alert;
import com.sfarc.monitor.entity.User;
import com.sfarc.monitor.repository.AlertRepository;
import com.sfarc.monitor.repository.SensorRepository;
import com.sfarc.monitor.repository.UserRepository;
import com.sfarc.monitor.web.mappers.SensorDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sfarc.monitor.web.exception.FailedProcessingSensorDataException;


import javax.persistence.EntityNotFoundException;

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

	@Autowired
	private SensorDataMapper sensorDataMapper;



	/**
	 * Checking sensor data with defined logic s
	 *
	 * @param sensorDataDto The Sensor Data
	 */
	public void checkSensorData(SensorDataDto sensorDataDto)
	{
		Alert alert = sensorDataMapper.sensorDtaDtoToSensorData(sensorDataDto);
		Logic logic = logicFactory.findLogic( LogicFinder.findLogicName( alert.getSensorId() ) );

		try
		{
			if (logic.check( alert.getValue() ))
			{
				User user = userRepository.findUserByUserSensors( alert.getSensorId() ).orElseThrow(EntityNotFoundException::new);
				Sensor sensor = sensorRepository.findSensorBySensorId( alert.getSensorId() ).orElseThrow(EntityNotFoundException::new);
				NotificationModel notificationModel = notificationModelConverter.convert( user, sensor, alert );
				// emailNotifier.notifyUser( notificationModel );
				// voiceCallNotifier.notifyUser( notificationModel );
				// smsNotifier.notifyUser( notificationModel );

				alertRepository.save( alert ); // TODO: alertRepository should save alert data, not sensor data
			}

		}
		catch( Exception ex )
		{
			ex.printStackTrace();
			throw new FailedProcessingSensorDataException();

		}

	}
}
