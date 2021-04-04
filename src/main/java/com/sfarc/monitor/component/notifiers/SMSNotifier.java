package com.sfarc.monitor.component.notifiers;

import com.sfarc.monitor.component.model.NotificationModel;
import org.springframework.stereotype.Component;

@Component
public class SMSNotifier implements Notifier
{
	@Override
	public void notifyUser( NotificationModel notificationModel )
	{
		System.out.println("SMS will be send to user");
	}
}
