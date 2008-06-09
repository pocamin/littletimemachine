package org.littleTeamMachine.runner;

import org.littleTeamMachine.common.services.Controller;
import org.littleTeamMachine.service.jmsReceiver.NotificationReceiver;
import org.littleTeamMachine.ui.MainUI;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Runner {
	
	public static void main(String[] args){
		
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"applicationContext.xml"});
		
		final Controller controller = (Controller)context.getBean("myController");
		final NotificationReceiver notificationReceiver = (NotificationReceiver)context.getBean("myController");
		
		final MainUI mainUI = new MainUI(controller,notificationReceiver);
		mainUI.setVisible(true);//On la rend visible

		(new Thread(mainUI.getMainPooler())).start();
		

	}
	

}
