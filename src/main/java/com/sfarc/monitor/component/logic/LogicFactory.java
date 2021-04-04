package com.sfarc.monitor.component.logic;

import com.sfarc.monitor.component.logic.impl.TemperatureLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Optional;
import java.util.Set;

/**
 * @author Tharindu Aththnayake
 * @since 04/04/2021 01:01 AM
 */
@Component
public class LogicFactory
{
	private HashMap<LogicName, Logic> logics = new HashMap<>();

	public Logic findLogic( LogicName logicName)
	{
		System.out.println(logicName);
		Logic logic = null;

		if (logics.containsKey( logicName ))
		{
			logic = logics.get( logicName );
		}
		else
		{
			logic = createLogic( logicName );
			logics.put( logicName, logic );
		}

		return logic;
	}

	private Logic createLogic(LogicName logicName)
	{
		switch( logicName )
		{
			case TEMP_LOGIC:
				return new TemperatureLogic();

			default:
				return null;
		}
	}
}
