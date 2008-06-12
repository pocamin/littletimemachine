package org.littleTeamMachine.runner;

import org.littleTeamMachine.common.services.Controller;
import org.littleTeamMachine.common.services.ControllerLocation;
import org.littleTeamMachine.service.jmsReceiver.INotificationReceiver;
import org.littleTeamMachine.ui.security.LoginUI;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Runner {
	

	public static void main(String[] args){
		
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"applicationContext.xml"});
		
		final Controller controller = (Controller)context.getBean("controller");
		ControllerLocation.CONTROLLER = controller;
		final INotificationReceiver notificationReceiver = (INotificationReceiver)context.getBean("notificationReceiver");
		
		
		LoginUI loginUI = new LoginUI(controller,notificationReceiver);
		loginUI.setVisible(true);
		
		


	}
	

}
