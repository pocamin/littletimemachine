package org.littleTeamMachine.ui;

import java.awt.Color;
import java.util.Set;

import javax.swing.JButton;

import org.littleTeamMachine.common.modele.Task;
import org.littleTeamMachine.common.services.Controller;
import org.littleTeamMachine.service.Pooler.MainPooler;

public class JButtonTask extends JButton {
	
	
	private static final long serialVersionUID = 1L;

	boolean started=false;
	
	private Task task;
	private MainPooler pooler;
	

	
	public JButtonTask(Task task,Controller controller,MainPooler pooler) {
		super(task.getTaskType().getName());
		this.task = task;
		this.pooler = pooler;
		updateText();
		
	}

	public Task getTask() {
		return task;
	}
	
	public void toggle(Set<JButtonTask> otherButtons){
		
		setVisible(false);
		if (started){
			setBackground(null);
			pooler.setCurrentTask(null);			
		} else {
			for (JButtonTask buttonTask : otherButtons){
				if ( buttonTask.started){
					buttonTask.toggle(otherButtons);
				}
			}
			setBackground(Color.green);
		}
		started = !started;
		
		
		if (started)
			pooler.setCurrentTask(task);
		
		
		setVisible(true);
	}
	
	public void updateText(){
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
