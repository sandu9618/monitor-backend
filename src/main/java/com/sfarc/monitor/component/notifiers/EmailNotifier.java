package com.sfarc.monitor.component.notifiers;

import com.sfarc.monitor.component.model.NotificationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * @author Tharindu Aththnayake
 * @since 04/04/2021 01:52 PM
 */
@Component
public class EmailNotifier implements Notifier
{
	@Autowired
	private JavaMailSender javaMailSender;

	public void notifyUser( NotificationModel notificationModel ) throws MailException
	{
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(notificationModel.getUser().getEmail());
		mail.setSubject("Critical Sensor Reading Warning");
		mail.setText("Your implemented " + notificationModel.getSensor().getSensorName()
				+ " sensor has observed a critical value " + notificationModel.getCriticalValue()
				+ " at " + notificationModel.getTime() +"." );

		javaMailSender.send(mail);
	}
}
