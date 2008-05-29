package timeMachine.controller;

import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import timeMachine.dao.CategorieDAO;
import timeMachine.dao.TaskDAO;
import timeMachine.dao.TaskTypeDAO;
import timeMachine.dao.ThemeDAO;
import timeMachine.modele.Categorie;
import timeMachine.modele.Root;
import timeMachine.modele.Task;
import timeMachine.modele.TaskType;
import timeMachine.modele.Theme;



public class Controller  {

	TaskDAO taskDAO = TaskDAO.getInstance();

	
	public void createTasks(TaskType taskType, Date startDate, Date endDate, boolean forcasted, boolean dayL,
			boolean dayMa, boolean dayMe, boolean dayJ,
			boolean dayV, long time) {

		
		List<Date> dates = new ArrayList<Date>(); 
		
		if (!startDate.equals(endDate)){
			
			Calendar current = Calendar.getInstance();
			current.setTime(startDate);
			Calendar end = Calendar.getInstance();
			end.setTime(endDate);
			
			while( !current.after(end) ){
				switch (current.get(Calendar.DAY_OF_WEEK)){
					case Calendar.MONDAY:
						if (dayL)
							dates.add(current.getTime());
						break;
					case Calendar.TUESDAY:
						if (dayMa)
							dates.add(current.getTime());
						break;
					case Calendar.WEDNESDAY:
						if (dayMe)
							dates.add(current.getTime());
						break;
					case Calendar.FRIDAY:
						if (dayJ)
							dates.add(current.getTime());
						break;
					case Calendar.THURSDAY:
						if (dayV)
							dates.add(current.getTime());
						break;
				}
				current.add(Calendar.DAY_OF_MONTH, 1);
			} 
			
			
		} else dates.add(startDate);
		
		if (taskType.getId() == null){
			saveTaskType(taskType);
		}
		

		
		for (Date date : dates ){
			Task task = new Task();
			task.setTaskType(taskType);
			task.setForcasted(new Boolean(forcasted));
			task.setForecastTimeInMinute(time);
			task.setDay(date);
			
			if ( taskDAO.getTasks(date, taskType).size() == 0)
				taskDAO.save(task);
		}
		
	}

	public void saveTaskType(TaskType taskType) {
		if (taskType.getCategorie().getId() == null){
			CategorieDAO.getInstance().save(taskType.getCategorie());
		}
		if (taskType.getTheme().getId() == null){
			ThemeDAO.getInstance().save(taskType.getTheme());
		}
		TaskTypeDAO.getInstance().save(taskType);
	}

	public Collection<TaskType> getAvailableTaskTypes(){
		return TaskTypeDAO.getInstance().getAllAvailableTaskType();
		
	}
	
	public Collection<Task> getTasks(Date date) {
		return taskDAO.getTasks(date);
	}

	public void save(Task task) {
		taskDAO.save(task);
		
	}

	public Collection<Categorie> getAvailableCategories() {
		return CategorieDAO.getInstance().getAllAvailableCategories();
	}

	public Collection<Theme> getAvailableThemes() {
		return ThemeDAO.getInstance().getAllAvailableTheme();
	}
	
	public Collection<Task> getTasks(Date from, Date to){
		return TaskDAO.getInstance().getTasks(from, to);
	}

	public void refresh(Object o) {
		TaskDAO.getInstance().refresh(o);
	}

	public void delete(Root root) {
		taskDAO.delete(root);
	}
	
}
