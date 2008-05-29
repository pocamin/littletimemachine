package timeMachine.ui.calendar;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import timeMachine.controller.Controller;
import timeMachine.ui.MainUI;
import timeMachine.ui.calendar.components.ComponentRenderer;

public class CalendarUI extends JFrame{

	
	private static final long serialVersionUID = 1L;
	
	
	private JScrollPane panelCalendar ;
	private JPanel panelNav = new JPanel();
	private TaskTableModel model;
	private JTable table;
	private JButton buttonLeft = new JButton("<");
	private JButton buttonRight = new JButton(">");
	private Date currentDate = new Date();
	private Controller controller;
	private JLabel labelSemaine = new JLabel("");
	
	public CalendarUI(Controller c) {
		this.controller = c;
		build();
		setLocationRelativeTo(null);
	}
	
	
	
	
	/**
	 * Initialisation de la fenetre
	 */
	private void build(){
		table= new JTable();
		setTitle("Time Machine - Calendar"); 
		initCalendar();
		setLocationRelativeTo(MainUI.mainUI);
		table.setDefaultRenderer(Object.class, new ComponentRenderer());
		panelCalendar = new JScrollPane(table); 
		table.addMouseListener(new CalendarTableMouseListener(table,this));
		
		this.setLayout(new BorderLayout());
		panelNav.add(buttonLeft);
		panelNav.add(labelSemaine);
		panelNav.add(buttonRight);
		add(panelNav,BorderLayout.NORTH);
		
		add(panelCalendar ,BorderLayout.CENTER );

		setSize(700,400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		buttonLeft.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				changeDate(-1);
			}
			
		});

		buttonRight.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				changeDate(1);
			}


			
		});
		
		
	}
	
	private void changeDate(int sem) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		calendar.add(Calendar.WEEK_OF_YEAR, sem);
		currentDate = calendar.getTime();
		initCalendar();
		table.repaint();
	}

	@Override
	public void repaint() {
		super.repaint();
	}
	

	private void initCalendar() {
		
		model = new TaskTableModel(currentDate, controller,this);
		table.setModel(model);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		int w = calendar.get(Calendar.WEEK_OF_YEAR);
		
		
		labelSemaine.setText("Semaine : " + w);
	}


	public Controller getController() {
		return controller;
	}


	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	
	
}
