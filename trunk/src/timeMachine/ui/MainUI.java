package timeMachine.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import timeMachine.controller.Controller;
import timeMachine.controller.ModifListener;
import timeMachine.modele.Task;
import timeMachine.ui.calendar.CalendarUI;
import timeMachine.ui.calendar.components.Refreshable;
import timeMachine.ui.graph.GraphUI;

public class MainUI extends JFrame implements Refreshable{

	private final Set<JButtonTask> buttonTasks = new HashSet<JButtonTask>();
	
	private final JButton buttonAdd = new JButton("ajouter Tache"); 
	
	private static final long serialVersionUID = 1L;
	
	private JPanel panelTask = new JPanel(new GridLayout(0,1));
	
	private Controller controller;


	public static MainUI mainUI ;
	
	public MainUI(Controller controller) {
		super();
		mainUI = this;
		this.controller = controller;
		build();
		
	}

	
	/**
	 * Initialisation de la fenetre
	 */
	private void build(){
		setTitle("Little Time Machine"); 
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
				new CalendarUI(controller);
			}
		});
		
		JMenuItem menuStatistiques = new JMenuItem("Statistiques");
		operationMenu.add(menuStatistiques);
		menuStatistiques.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				new GraphUI(controller);
			}
		});
		
		JMenu operationConfig = new JMenu("Configuration");
		menuBar.add(operationConfig);
		JCheckBoxMenuItem checkBoxMenuItem = new JCheckBoxMenuItem("Toujours visible",true);
		operationConfig.add(checkBoxMenuItem);
		checkBoxMenuItem.addItemListener(new ItemListener(){

			public void itemStateChanged(ItemEvent arg0) {
				setAlwaysOnTop(((JCheckBoxMenuItem)arg0.getItemSelectable()).isSelected());
			}});
		
		
		setJMenuBar(menuBar);
	}
	
	
	public void initJbuttonTask(){
		
		panelTask.removeAll();
		
		Collection<Task> tasks = controller.getTasks(new Date());
		
		for (Task task : tasks ){
			final JButtonTask buttonTask = new JButtonTask(task,controller);
			
			if (!buttonTasks.contains(buttonTask))
				buttonTasks.add(buttonTask);
			
			buttonTask.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					buttonTask.toggle(buttonTasks);
			}});
			
			task.addListener(new ModifListener(){
				public void doDelete() {
					panelTask.remove(buttonTask);
					setSize(200,(buttonTasks.size() + 1) * 20 + 70);
					setVisible(true);
				}

				public void doUpdate() {
					buttonTask.updateText();
				}});
			
		}

		for (JButtonTask task : buttonTasks){
			panelTask.add(task);
		}
		
		setSize(200,(buttonTasks.size() + 1) * 20 + 70);
		
		
		this.setVisible(true);
		
	}


	public void refresh() {
		//TODO : initJbuttonTask();
		
		for (JButtonTask task : buttonTasks){
			task.updateText();
			
		}
		
	}
	

}
