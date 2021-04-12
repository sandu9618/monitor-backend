package com.sfarc.monitor.component.notifiers;

import com.sfarc.monitor.component.model.NotificationModel;

/**
 * @author Tharindu Aththnayake
 * @since 04/04/2021 01:59 PM
 */
public interface Notifier
{
	void notifyUser( NotificationModel notificationModel );
}
