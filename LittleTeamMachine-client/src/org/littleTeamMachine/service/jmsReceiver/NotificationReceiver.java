package org.littleTeamMachine.service.jmsReceiver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.littleTeamMachine.common.modele.Root;
import org.littleTeamMachine.common.services.Controller;
import org.littleTeamMachine.common.services.ModificationNotifier;
import org.littleTeamMachine.service.notification.NotificationListener;
import org.springframework.beans.BeanUtils;

import static org.littleTeamMachine.common.services.ModificationNotifier.*;

public class NotificationReceiver implements MessageListener {

	private Controller controller;
	
	
	
	// weak hashMap qui va permetre de supprimer des 
	Map<Object,List<NotificationListener> > lifeCycleMap = new WeakHashMap<Object, List<NotificationListener>>();
	
	public void onMessage(Message message) {
		
 
		try {
			// On récupère le message
			ModificationNotifier notifier = (ModificationNotifier)((ObjectMessage)message).getObject();
			
			Root root = null;
			
			// On effectue l'action
			for ( List<NotificationListener> notifications : lifeCycleMap.values()){
				for ( NotificationListener notificationListener : notifications ){
					if (notificationListener.equals(notifier)){
						if (notifier.getType() == DELETED_NOTIFICATION )
							notificationListener.doDelete();
						else {
							
							if (root == null){
								root = controller.refresh(notificationListener.getRoot());
								BeanUtils.copyProperties(root, notificationListener.getRoot());
							} else {
								BeanUtils.copyProperties(root, notificationListener.getRoot());
							}
							
							notificationListener.doUpdate();
						}
					}
				}
			}
			
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Ajoute un listener de modification
	 * @param notificationListener
	 * @param lifeCycleLinkedObject
	 */
	public void addNotificationListener( NotificationListener notificationListener, Object lifeCycleLinkedObject){
		List<NotificationListener> notifications =lifeCycleMap.get(lifeCycleLinkedObject);
		if (notifications == null){
			notifications = new ArrayList<NotificationListener>();
			lifeCycleMap.put(lifeCycleLinkedObject, notifications);
		}
		notifications.add(notificationListener);
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	
}

	
	 
	
	
	
	
	
	


