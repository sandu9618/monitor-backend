package com.sfarc.monitor.component.logic.impl;

import com.sfarc.monitor.component.logic.Logic;
import com.sfarc.monitor.component.logic.LogicName;
import org.springframework.stereotype.Component;

/**
 * @author Tharindu Aththnayake
 * @since 04/04/2021 12:54 AM
 */
@Component
public class TemperatureLogic implements Logic
{
	@Override public boolean check(String dataValue)
	{
		double checkingValue = Double.parseDouble( dataValue.substring( 0 , dataValue.length() - 1 ) );
		return checkingValue > 20;
	}

	@Override public LogicName getLogicName()
	{
		return LogicName.TEMP_LOGIC;
	}
}
