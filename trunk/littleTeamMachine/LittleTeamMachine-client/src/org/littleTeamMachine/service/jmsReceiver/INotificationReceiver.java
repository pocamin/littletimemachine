package org.littleTeamMachine.service.jmsReceiver;

import org.littleTeamMachine.service.notification.NotificationListener;

public interface INotificationReceiver {
	public void addNotificationListener( NotificationListener notificationListener, Object lifeCycleLinkedObject);

}