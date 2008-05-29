package timeMachine.ui.calendar.components;

import javax.swing.JComponent;
import javax.swing.JFrame;

import timeMachine.controller.Controller;
import timeMachine.controller.ModifListener;
import timeMachine.modele.TaskType;

public class TaskTypeComponentListener implements ModifListener {

	private JComponent[][] components;
	private int row;
	private int col;
	private TaskType taskType;
	private Controller controller;
	private JFrame frame;

	public TaskTypeComponentListener(JComponent[][] components, int row, int col,
			TaskType taskType, Controller c, JFrame f) {
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
