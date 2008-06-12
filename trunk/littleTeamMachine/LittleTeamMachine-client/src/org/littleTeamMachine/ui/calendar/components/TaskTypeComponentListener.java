package org.littleTeamMachine.ui.calendar.components;

import java.lang.ref.WeakReference;

import javax.swing.JComponent;
import javax.swing.JFrame;

import org.littleTeamMachine.common.modele.TaskType;
import org.littleTeamMachine.common.services.Controller;
import org.littleTeamMachine.service.notification.NotificationListener;



public class TaskTypeComponentListener extends NotificationListener {

	private WeakReference<JComponent[][]> components;
	private int row;
	private int col;
	private TaskType taskType;
	private WeakReference<Controller> controller;
	private WeakReference<JFrame> frame;
	
	public TaskTypeComponentListener(JComponent[][] components, int row, int col,
			TaskType taskType, Controller c, JFrame f) {
		super(taskType);
		this.components = new WeakReference<JComponent[][]>(components);
		this.row = row;
		this.col = col;
		this.taskType = taskType;
		controller = new WeakReference<Controller>(c);
		frame = new WeakReference<JFrame>(f);
	}

	public void doDelete() {
		components.get()[row][col] = null;
	}

	public void doUpdate() {
		components.get()[row][col] = new TaskTypeComponent(taskType,controller.get(),frame.get());
	}

}
