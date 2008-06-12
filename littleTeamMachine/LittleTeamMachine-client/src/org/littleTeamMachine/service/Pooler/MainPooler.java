package org.littleTeamMachine.service.Pooler;

import java.util.Date;


import org.littleTeamMachine.common.modele.Task;
import org.littleTeamMachine.common.services.Controller;
import org.littleTeamMachine.service.LTMContext;

public class MainPooler implements Runnable {
	
	private Task currentTask;
	
	private Controller controller; 
	
	private long startedTimeStamp = 0;

	private boolean isStopped = true;
	private static final long oneMinute = 60000;
	
	public MainPooler(Controller controller) {
		this.controller = controller;
	}


	private synchronized void updateTimeTaken() {
		long minutes = (new Date().getTime() - startedTimeStamp) / oneMinute;
		if (minutes > 0){
			startedTimeStamp = startedTimeStamp + oneMinute*minutes;
			currentTask.setTimeTakenInMinute( currentTask.getTimeTakenInMinute() + minutes);
			currentTask = controller.save(LTMContext.getCertificat(),currentTask);
		}
		
	}

	
	public void run() {
		while (!isStopped){
			try {
				if (currentTask != null){
					updateTimeTaken();
				}
				Thread.sleep(500);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}


	public Task getCurrentTask() {
		return currentTask;
	}


	public synchronized  void setCurrentTask(Task currentTask) {
		this.currentTask = currentTask;
		if (currentTask != null){
			startedTimeStamp = new Date().getTime();
		}
	}


	public void stop() {
		isStopped  = true;
		
	}



	
	
	
	
	
	

}
