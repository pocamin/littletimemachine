package org.littleTeamMachine.ui.calendar.components;

import javax.swing.JComponent;
import javax.swing.JFrame;

import org.littleTeamMachine.common.modele.TaskType;
import org.littleTeamMachine.common.services.Controller;
import org.littleTeamMachine.service.notification.NotificationListener;



public class TaskTypeComponentListener extends NotificationListener {

	private JComponent[][] components;
	private int row;
	private int col;
	private TaskType taskType;
	private Controller controller;
	private JFrame frame;

	public TaskTypeComponentListener(JComponent[][] components, int row, int col,
			TaskType taskType, Controller c, JFrame f) {
		super(taskType);
		this.components = components;
		this.row = row;
		this.col = col;
		this.taskType = taskType;
		this.controller = c;
		this.frame = f;
	}

	public void doDelete() {
		components[row][col] = null;
	}

	public void doUpdate() {
		components[row][col] = new TaskTypeComponent(taskType,controller,frame);
	}

}
