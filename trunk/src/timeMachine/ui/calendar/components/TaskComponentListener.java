package timeMachine.ui.calendar.components;

import javax.swing.JComponent;
import javax.swing.JFrame;

import timeMachine.controller.Controller;
import timeMachine.controller.ModifListener;
import timeMachine.modele.Task;

public class TaskComponentListener implements ModifListener {

	private JComponent[][] components;
	private int row;
	private int col;
	private Task task;
	private Controller controller;
	private JFrame frame;

	public TaskComponentListener(JComponent[][] components, int row, int col,
			Task task, Controller c, JFrame f) {
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
