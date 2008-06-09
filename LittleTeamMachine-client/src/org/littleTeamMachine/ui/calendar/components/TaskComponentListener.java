package org.littleTeamMachine.ui.calendar.components;

import javax.swing.JComponent;
import javax.swing.JFrame;

import org.littleTeamMachine.common.modele.Task;
import org.littleTeamMachine.common.services.Controller;
import org.littleTeamMachine.service.notification.NotificationListener;



public class TaskComponentListener extends NotificationListener {

	private JComponent[][] components;
	private int row;
	private int col;
	private Task task;
	private Controller controller;
	private JFrame frame;

	public TaskComponentListener(JComponent[][] components, int row, int col,
			Task task, Controller c, JFrame f) {
		super(task);
		this.components = components;
		this.row = row;
		this.col = col;
		this.task = task;
		this.controller = c;
		this.frame = f;
	}

	public void doDelete() {
		components[row][col] = null;
	}

	public void doUpdate() {
		components[row][col] = new TaskComponent(task,controller,frame);
	}

}
