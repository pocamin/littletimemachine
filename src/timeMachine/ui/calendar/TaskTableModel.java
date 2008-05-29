package timeMachine.ui.calendar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.table.AbstractTableModel;

import timeMachine.controller.Controller;
import timeMachine.modele.Task;
import timeMachine.modele.TaskType;
import timeMachine.tools.DateTool;
import timeMachine.ui.calendar.components.TaskComponent;
import timeMachine.ui.calendar.components.TaskComponentListener;
import timeMachine.ui.calendar.components.TaskTypeComponent;
import timeMachine.ui.calendar.components.TaskTypeComponentListener;

public class TaskTableModel extends AbstractTableModel {
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
	
	private JComponent[][] components;
	
	//private TaskType[] taskTypes = new TaskType[0];
	private Date[] dates = new Date[5]; 
	
	Map<Date,Map<TaskType,Task>> allTasksByDay = new HashMap<Date, Map<TaskType,Task>>();
	
	Collection<Task> allTasks;
	
	
	Controller controller;
	
	private static final long serialVersionUID = 1L;
	
	private Calendar firstDayOfCurrentWeek;
	private String[] columnNames = new String[5];
	
	@Override
	public String getColumnName(int i) {
		if (i == 0)
			return null;
		
		return columnNames[i-1];
	}
	
	public TaskTableModel(Date date, final Controller c, final JFrame f) {
		controller = c;
		this.firstDayOfCurrentWeek = Calendar.getInstance();
		firstDayOfCurrentWeek.setTime(DateTool.normalizeDate(date));
		firstDayOfCurrentWeek.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
		Set<TaskType> tmpTaskTypes = new HashSet<TaskType>();
		
		Calendar tmpCalendar = (Calendar)firstDayOfCurrentWeek.clone();
		for (int i = 0 ; i < 5 ; i ++){
			
			Date datetmp = tmpCalendar.getTime();
			dates[i] = datetmp;
			tmpCalendar.add(Calendar.DAY_OF_MONTH, 1);
			
			
			// On récupere les noms de colone
			columnNames[i] = dateFormat.format(datetmp);
			
			
			// On récupère les taches
			allTasksByDay.put(datetmp, new HashMap<TaskType, Task>());
			Collection<Task> tasks = c.getTasks(datetmp);
			for (Task task : tasks){
				tmpTaskTypes.add(task.getTaskType());
				allTasksByDay.get(datetmp).put(task.getTaskType(), task);
			}

			
			
		}
		
		
		// On rempli le tableau de composant.
		components = new JComponent[6][tmpTaskTypes.size()];
		int j =0;
		for ( final TaskType taskType : tmpTaskTypes ){
			
			components[0][j] = new TaskTypeComponent(taskType,c,f);
			
			taskType.addListener(new TaskTypeComponentListener(components,0,j,taskType,c,f));
			
			
			tmpCalendar = (Calendar)firstDayOfCurrentWeek.clone();
			for (int i = 1 ; i < 6 ; i ++){
				
				Task task = allTasksByDay.get(dates[i-1]).get(taskType);
				
				if (task != null){
					components[i][j] = new TaskComponent(task,c,f);
					task.addListener(new TaskComponentListener(components,i,j,task,c,f));
					
				}
				
			}
			j++;
		}
	}
	
	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}
	
	
	public int getColumnCount() {
		return components.length;
	}

	public int getRowCount() {
		return components[0].length;
	}

	public Object getValueAt(int y, int x) {
		return components[x][y];
	}
	
	
	public Class<?> getColumnClass(int column) {
		if (components != null && components[column].length > 0 )
			return Object.class;
		
		return components[column][0].getClass();
	}
	
	
	

}
