package com.sfarc.monitor.component.logic;

/**
 * @author Tharindu Aththnayake
 * @since 04/04/2021 12:45 AM
 */
public interface Logic
{
	boolean check(String dataValue);

	LogicName getLogicName();

}
