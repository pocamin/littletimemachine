package org.littleTeamMachine.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import org.littleTeamMachine.common.modele.Task;
import org.littleTeamMachine.common.services.Controller;
import org.littleTeamMachine.service.Pooler.MainPooler;
import org.littleTeamMachine.service.jmsReceiver.NotificationReceiver;
import org.littleTeamMachine.service.notification.NotificationListener;
import org.littleTeamMachine.ui.calendar.CalendarUI;
import org.littleTeamMachine.ui.graph.GraphUI;


public class MainUI extends JFrame implements Serializable{

	private final Set<JButtonTask> buttonTasks = new HashSet<JButtonTask>();
	
	private final JButton buttonAdd = new JButton("ajouter Tache"); 
	
	private static final long serialVersionUID = 1L;
	
	private JPanel panelTask = new JPanel(new GridLayout(0,1));
	
	private Controller controller;

	private NotificationReceiver notificationReceiver;

	private MainPooler mainPooler; 
	
	
	public static MainUI mainUI ;
	
	public MainUI(Controller controller, NotificationReceiver notificationReceiver) {
		super();
		mainUI = this;
		this.controller = controller;
		this.notificationReceiver = notificationReceiver;
		mainPooler  = new MainPooler(controller);
		build();
		
	}

	
	/**
	 * Initialisation de la fenetre
	 */
	private void build(){
		setTitle("Time Machine"); 
		this.setLayout(new BorderLayout());
		add(panelTask,BorderLayout.CENTER);
		
		
		initJbuttonTask();
		setLocationRelativeTo(null);
		setResizable(true); 
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		buttonAdd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				JDialog dialog =  new CreateTaskUI(controller,mainUI);
				dialog.setVisible(true);
				initJbuttonTask();
			}
		});
		
		add(buttonAdd,BorderLayout.SOUTH);
		
		JMenuBar menuBar = new JMenuBar();
		JMenu operationMenu = new JMenu("Operation");
		menuBar.add(operationMenu);
		JMenuItem menuCalendrier = new JMenuItem("Calendrier");
		operationMenu.add(menuCalendrier);
		menuCalendrier.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				new CalendarUI(controller,notificationReceiver);
			}
		});
		
		JMenuItem menuStatistiques = new JMenuItem("Statistiques");
		operationMenu.add(menuStatistiques);
		menuStatistiques.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				new GraphUI(controller);
			}
		});
		
		setJMenuBar(menuBar);
	}
	
	
	public void initJbuttonTask(){
		
		panelTask.removeAll();
		
		Collection<Task> tasks = controller.getTasks(new Date());
		
		for (Task task : tasks ){
			final JButtonTask buttonTask = new JButtonTask(task,controller,mainPooler);
			
			if (!buttonTasks.contains(buttonTask))
				buttonTasks.add(buttonTask);
			
			buttonTask.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					buttonTask.toggle(buttonTasks);
			}});
			
			
			NotificationListener notificationListener = new NotificationListener(task){

				@Override
				public void doDelete() {
					panelTask.remove(buttonTask);
					setSize(200,(buttonTasks.size() + 1) * 20 + 70);
					setVisible(true);
				}

				@Override
				public void doUpdate() {
					buttonTask.updateText();
				}};
			
			notificationReceiver.addNotificationListener(notificationListener, buttonTask);
			
			
		}

		for (JButtonTask task : buttonTasks){
			panelTask.add(task);
		}
		
		setSize(200,(buttonTasks.size() + 1) * 20 + 70);
		
		
		this.setVisible(true);
		
	}


	public MainPooler getMainPooler() {
		return mainPooler;
	}

}
