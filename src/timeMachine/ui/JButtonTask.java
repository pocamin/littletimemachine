package timeMachine.ui;

import java.awt.Color;
import java.util.Date;
import java.util.Set;

import javax.swing.JButton;

import timeMachine.controller.Controller;
import timeMachine.modele.Task;

public class JButtonTask extends JButton {
	private static final long oneMinute = 60000;
	
	
	private static final long serialVersionUID = 1L;

	boolean started=false;
	long startedTimeStamp = new Date().getTime(); 
	
	private Task task;
	private Controller controller; 
	

	
	public JButtonTask(Task task,Controller controller) {
		super(task.getTaskType().getName());
		this.task = task;
		this.controller = controller;
		updateText();
		
	}

	public Task getTask() {
		return task;
	}
	
	public void toggle(Set<JButtonTask> otherButtons){
		setVisible(false);
		if (started){
			// TODO
			setBackground(null);
			
			updateTimeTaken();
			
			
			
			startedTimeStamp = new Date().getTime();
			updateText();
			
			
			
			
		} else {
			for (JButtonTask buttonTask : otherButtons){
				if ( buttonTask.started){
					buttonTask.toggle(otherButtons);
				}
			}
			startedTimeStamp = new Date().getTime();
			
			setBackground(Color.green);
		}
		started = !started;
		setVisible(true);
	}
	
	private synchronized void updateTimeTaken() {
		
		long minutes = (new Date().getTime() - startedTimeStamp) / oneMinute;
		if (minutes > 0){
			startedTimeStamp = startedTimeStamp + oneMinute*minutes;
			task.setTimeTakenInMinute( task.getTimeTakenInMinute() + minutes);
			controller.save(task);
		}
		
	}

	public void updateText(){
		if (started)
			updateTimeTaken();
		//controller.refresh(task);
		String text = task.getTaskType().getName();
		text +=  "  (";
		text +=  task.getTimeTakenInMinute();
		text +=  "/";
		text +=  task.getForecastTimeInMinute();
		text +=  ")";
		setText(text);
	}
	
	
	@Override
	public boolean equals(Object arg0) {
		if (arg0 instanceof JButtonTask) {
			JButtonTask buttonTask = (JButtonTask) arg0;
			return buttonTask.getTask().equals(buttonTask.getTask());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return task.hashCode();
	}
	
	public String toString() {
		return getTask().getTaskType().getName();
	}


	
}
