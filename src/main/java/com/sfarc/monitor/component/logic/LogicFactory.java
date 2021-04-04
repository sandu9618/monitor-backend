package com.sfarc.monitor.component.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Set;

/**
 * @author Tharindu Aththnayake
 * @since 04/04/2021 01:01 AM
 */
@Component
public class LogicFactory
{
	private HashMap<LogicName, Logic> logics;

	@Autowired
	public LogicFactory( Set< Logic > logicSet )
	{
		createLogic( logicSet );
	}

	public Logic findLogic(LogicName logicName)
	{
		return logics.get(logicName);
	}

	private void createLogic(Set<Logic> logicSet)
	{
		logics = new HashMap<LogicName, Logic>();
		logicSet.forEach(
				logic ->logics.put(logic.getLogicName(), logic));
	}
}
