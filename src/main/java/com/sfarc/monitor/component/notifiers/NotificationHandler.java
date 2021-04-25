package com.sfarc.monitor.component.notifiers;

import com.sfarc.monitor.component.model.NotificationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationHandler
{
	@Autowired
	private EmailNotifier emailNotifier;

	@Autowired
	private SMSNotifier smsNotifier;

	@Autowired
	private VoiceCallNotifier voiceCallNotifier;

	public void notifyAll( final NotificationModel notificationModel ){

		notificationModel.getUser().getUserNotifiers()
				.stream()
				.forEach( e -> sendNotifications( e, notificationModel ) );

	}

	private void sendNotifications( NotifierType notifierType, NotificationModel notificationModel ){

		switch( notifierType ){
			case EMAIL:
				emailNotifier.notifyUser( notificationModel );
				break;

			case SMS:
				smsNotifier.notifyUser( notificationModel );
				break;

			case VOICE_CALL:
				voiceCallNotifier.notifyUser( notificationModel );
				break;

			default:
				System.out.println( "Invalid Notification Type" );
		}
	}



}
