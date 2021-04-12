package com.sfarc.monitor.component.logic;

import org.springframework.stereotype.Component;

/**
 * @author Tharindu Aththnayake
 * @since 04/04/2021 01:22 AM
 */
@Component
public class LogicFinder
{
	public static LogicName findLogicName(String sensorId)
	{
		String sensorTypePortionInSensorId = sensorId.substring( 0, 2 );

		System.out.println(sensorTypePortionInSensorId);
		switch( sensorTypePortionInSensorId )
		{
			case "TE":
				return LogicName.TEMP_LOGIC;

			case "HU":
				return LogicName.HUMID_LOGIC;

			case "PR":
				return LogicName.PRESSURE_LOGIC;

			default:
				return null;
		}
	}

}
