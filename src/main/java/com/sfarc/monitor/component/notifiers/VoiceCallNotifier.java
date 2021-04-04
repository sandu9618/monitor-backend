package com.sfarc.monitor.component.notifiers;

import com.sfarc.monitor.component.model.NotificationModel;
import org.springframework.stereotype.Component;

@Component
public class VoiceCallNotifier implements Notifier
{
	@Override
	public void notifyUser( NotificationModel notificationModel )
	{
		System.out.println("Voice Call will be directed to user");
	}
}
