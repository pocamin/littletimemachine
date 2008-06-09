package org.littleTeamMachine.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import org.littleTeamMachine.common.modele.Task;
import org.littleTeamMachine.common.services.Controller;

public class UpdateTaskUI extends JDialog{

	private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
	
	private static final long serialVersionUID = 1L;
	private Controller controller;

	
	private JTextField date = new JTextField() ; 
	private JTextField timeInMinute = new JTextField();
	private JTextField timeTakenInMinute = new JTextField();
	private JButton ok = new JButton("OK");
	private JButton delete = new JButton("DEL");
	private JCheckBox forcasted = new JCheckBox();

	
	private UpdateTaskUI createTaskUI = this;

	private Task task;
	
	private void build() {
		setTitle("Time Machine - maj tâche");
		this.setSize(300, 300);
		
		
		JPanel panel = new JPanel();
		
		this.setLayout(new BorderLayout());
		
		panel.setLayout(new SpringLayout());
		
		panel.add(new JLabel("name"));
		panel.add(new JLabel(task.getTaskType().getName()));

		panel.add(new JLabel("categorie"));
		panel.add(new JLabel(task.getTaskType().getCategorie().getName()));

		panel.add(new JLabel("Theme"));
		panel.add(new JLabel(task.getTaskType().getTheme().getName()));
		
		
		date.setText(dateFormat.format(task.getDay()));
		panel.add(new JLabel("Date"));
		panel.add(date);
		
		
		forcasted.setSelected(task.getForcasted().booleanValue());
		panel.add(new JLabel("Prévue ?"));
		panel.add(forcasted);		

		
		
		timeInMinute.setText(task.getForecastTimeInMinute().toString());
		panel.add(new JLabel("durée prévue"));
		panel.add(timeInMinute);		

		timeTakenInMinute.setText(task.getTimeTakenInMinute().toString());
		panel.add(new JLabel("durée"));
		panel.add(timeTakenInMinute);		
		
		
		this.add(panel,BorderLayout.CENTER);
		
		
		JPanel panelBtn = new JPanel(); 
		panelBtn.add(ok);
		panelBtn.add(delete);
		this.add(panelBtn,BorderLayout.SOUTH);
		
		
		
		SpringUtilities.makeCompactGrid(panel, 7, 2, //rows, cols
			        6, 6, //initX, initY
			        6, 6); //xPad, yPad
		
		
		ok.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					Date tmpDate = dateFormat.parse(date.getText().toString());
					Long tmpTimeInMinute = new Long(timeInMinute.getText());
					Long tmpTimeTakenInMinute = new Long(timeTakenInMinute.getText());
					
					task.setDay(tmpDate);
					task.setForecastTimeInMinute(tmpTimeInMinute);
					task.setTimeTakenInMinute(tmpTimeTakenInMinute);
					task.setForcasted(forcasted.isSelected());
					task = controller.save(task);
					
					createTaskUI.dispose();
				} catch (NumberFormatException e) {
				} catch (ParseException e) {
				}
				
			}
			
		});
		

		delete.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				controller.delete(task);
				createTaskUI.dispose();
			}
			
		});
		
	}
	
	
	public UpdateTaskUI(Controller controller, JFrame frame, Task task) {
		super(frame,true);
		setLocationRelativeTo(frame);
		this.controller = controller;
		this.task = task;
		build();
	}
	
	
	



}
