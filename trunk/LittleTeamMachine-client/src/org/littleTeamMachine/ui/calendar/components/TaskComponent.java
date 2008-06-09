package org.littleTeamMachine.ui.calendar.components;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

import org.littleTeamMachine.common.modele.Task;
import org.littleTeamMachine.common.services.Controller;
import org.littleTeamMachine.ui.UpdateTaskUI;

public class TaskComponent extends JButton implements Refreshable{
	

	private static final long serialVersionUID = 1L;
	
	private Task task;
	
	
	public TaskComponent(final Task task,final  Controller controller,final JFrame frame) {
		super();
		this.task = task;
		refresh();
		
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JDialog dialog = new UpdateTaskUI(
						controller,
						frame, task);
				dialog.setVisible(true);
			}
		});
	}

	public void refresh() {
		setText(task.toString());

		if (task.getTimeTakenInMinute() != 0) {
			if (task.getTimeTakenInMinute().compareTo(
					task.getForecastTimeInMinute()) > 0) {
				setBackground(Color.RED);
				setForeground(Color.WHITE);
			} else {
				setBackground(Color.GREEN);
				setForeground(Color.BLACK);
			}
		} else {
			setBackground(Color.WHITE);
			setForeground(Color.BLACK);
		}
	}

	

}
