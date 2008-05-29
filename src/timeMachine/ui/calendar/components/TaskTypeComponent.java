package timeMachine.ui.calendar.components;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

import timeMachine.controller.Controller;
import timeMachine.modele.TaskType;
import timeMachine.ui.UpdateTaskTypeUI;

public class TaskTypeComponent extends JButton implements Refreshable{

	private static final long serialVersionUID = 1L;
	
	private TaskType taskType;
	

	public TaskTypeComponent(final TaskType taskType,final  Controller controller
			,final	JFrame frame) {
		super();
		this.taskType = taskType;
		refresh();
		
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JDialog dialog = new UpdateTaskTypeUI(
						controller,
						frame, taskType);
				dialog.setVisible(true);
			}
		});
	}




	public void refresh() {
		setText(taskType.toString());
		if (taskType.getFinished().booleanValue()) {
			setBackground(Color.GREEN);
			setForeground(Color.BLACK);
		} else {
			setBackground(Color.WHITE);
			setForeground(Color.BLACK);
		}
	}

	
}
