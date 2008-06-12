package org.littleTeamMachine.ui.calendar.components;

import java.lang.ref.WeakReference;

import javax.swing.JComponent;
import javax.swing.JFrame;

import org.littleTeamMachine.common.modele.Task;
import org.littleTeamMachine.common.services.Controller;
import org.littleTeamMachine.service.notification.NotificationListener;



public class TaskComponentListener extends NotificationListener {

	private WeakReference<JComponent[][]> components;
	private int row;
	private int col;
	private Task task;
	private WeakReference<Controller> controller;
	private WeakReference<JFrame> frame;

	public TaskComponentListener(JComponent[][] components, int row, int col,
			Task task, Controller c, JFrame f) {
		super(task);
		this.components = new WeakReference<JComponent[][]>(components);
		this.row = row;
		this.col = col;
		this.task = task;
		controller = new WeakReference<Controller>(c);
		frame = new WeakReference<JFrame>(f);
	}

	public void doDelete() {
		components.get()[row][col] = null;
	}

	public void doUpdate() {
		components.get()[row][col] = new TaskComponent(task,controller.get(),frame.get());
		frame.get().repaint();
	}

}
