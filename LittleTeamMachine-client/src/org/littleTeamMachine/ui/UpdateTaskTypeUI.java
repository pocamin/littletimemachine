package org.littleTeamMachine.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import org.littleTeamMachine.common.modele.Categorie;
import org.littleTeamMachine.common.modele.TaskType;
import org.littleTeamMachine.common.modele.Theme;
import org.littleTeamMachine.common.services.Controller;


public class UpdateTaskTypeUI extends JDialog{

	private static final long serialVersionUID = 1L;
	private Controller controller;
	
	private JTextField name = new JTextField() ; 
	
	private JComboBox themes = new JComboBox();
	private JComboBox categories = new JComboBox();
	private JCheckBox finished = new JCheckBox();
	private JButton ok = new JButton("OK");
	
	
	private UpdateTaskTypeUI updateTaskUI = this;

	private TaskType taskType;
	
	private void build() {
		setTitle("Time Machine - update task type");
		this.setSize(300, 300);
		
		name.setText(taskType.getName());
		initThemes();
		initCategories();
		
		/*
		theme.setText(taskType.getTheme().getName());
		categorie.setText(taskType.getCategorie().getName());
		*/
		
		finished.setSelected(taskType.getFinished().booleanValue());
		
		
		JPanel panel = new JPanel();
		
		this.setLayout(new BorderLayout());
		
		panel.setLayout(new SpringLayout());
		
		panel.add(new JLabel("name"));
		panel.add(name);

		panel.add(new JLabel("categorie"));
		panel.add(categories);

		panel.add(new JLabel("theme"));
		panel.add(themes);
		
		
		
		panel.add(new JLabel("terminée"));
		panel.add(finished);		

		
		
		this.add(panel,BorderLayout.CENTER);
		
		this.add(ok,BorderLayout.SOUTH);
		
		
		SpringUtilities.makeCompactGrid(panel, 4, 2, //rows, cols
			        6, 6, //initX, initY
			        6, 6); //xPad, yPad
		
		
		ok.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0) {
				Categorie categorie;
				if (categories.getSelectedItem() instanceof Categorie ){
					categorie = (Categorie) categories.getSelectedItem();
				} else {
					categorie = new Categorie();
					categorie.setName(categories.getSelectedItem().toString());
				}
				taskType.setCategorie(categorie);
				Theme theme;
				if (themes.getSelectedItem() instanceof Theme ){
					theme = (Theme) themes.getSelectedItem();
				} else {
					theme = new Theme();
					theme.setName(themes.getSelectedItem().toString());
				}
				taskType.setTheme(theme);
				taskType.setFinished(finished.isSelected());
				taskType.setName(name.getText());
				taskType = controller.save(taskType);
				updateTaskUI.dispose();
			}
			
		});
		

		
		
	}
	
	
	public UpdateTaskTypeUI(Controller controller, JFrame frame, TaskType taskType) {
		super(frame,true);
		setLocationRelativeTo(frame);
		this.controller = controller;
		this.taskType = taskType;
		build();
	}
	
	
	private void initCategories() {
		categories.setEditable(true);
		Collection<Categorie> categorieC =  controller.getAvailableCategories();
		for(Categorie categorie : categorieC ){
			categories.addItem(categorie);
		}
		categories.setSelectedItem(taskType.getCategorie());
	}

	private void initThemes() {
		themes.setEditable(true);
		Collection<Theme> themesC =  controller.getAvailableThemes();
		for(Theme theme : themesC ){
			themes.addItem(theme);
		}
		themes.setSelectedItem(taskType.getTheme());
	}



}
