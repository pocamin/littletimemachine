package org.littleTeamMachine.srv.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;


import org.littleTeamMachine.common.modele.Categorie;
import org.littleTeamMachine.common.modele.Root;
import org.littleTeamMachine.common.modele.Task;
import org.littleTeamMachine.common.modele.TaskType;
import org.littleTeamMachine.common.modele.Theme;
import org.littleTeamMachine.common.services.Controller;
import org.littleTeamMachine.srv.dao.CategorieDAO;
import org.littleTeamMachine.srv.dao.TaskDAO;
import org.littleTeamMachine.srv.dao.TaskTypeDAO;
import org.littleTeamMachine.srv.dao.ThemeDAO;







public class ControllerImpl implements Controller  {
	private TaskDAO taskDAO;
	private CategorieDAO categorieDAO;
	private TaskTypeDAO taskTypeDAO;
	private ThemeDAO themeDAO;

	
	/* (non-Javadoc)
	 * @see org.littleTeamMachine.srv.controller.Controller#createTasks(org.littleTimeMachine.common.modele.TaskType, java.util.Date, java.util.Date, boolean, boolean, boolean, boolean, boolean, boolean, long)
	 */
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
			save(taskType);
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


	/* (non-Javadoc)
	 * @see org.littleTeamMachine.srv.controller.Controller#getAvailableTaskTypes()
	 */
	public Collection<TaskType> getAvailableTaskTypes(){
		return taskTypeDAO.getAllAvailableTaskType();
		
	}
	
	/* (non-Javadoc)
	 * @see org.littleTeamMachine.srv.controller.Controller#getTasks(java.util.Date)
	 */
	public Collection<Task> getTasks(Date date) {
		return taskDAO.getTasks(date);
	}

	/* (non-Javadoc)
	 * @see org.littleTeamMachine.srv.controller.Controller#save(org.littleTimeMachine.common.modele.Task)
	 */

	public <T extends Root> T save(T o){ 
		taskDAO.save(o);
		return o;
		
	}

	/* (non-Javadoc)
	 * @see org.littleTeamMachine.srv.controller.Controller#getAvailableCategories()
	 */
	public Collection<Categorie> getAvailableCategories() {
		return categorieDAO.getAllAvailableCategories();
	}

	/* (non-Javadoc)
	 * @see org.littleTeamMachine.srv.controller.Controller#getAvailableThemes()
	 */
	public Collection<Theme> getAvailableThemes() {
		return themeDAO.getAllAvailableTheme();
	}
	
	/* (non-Javadoc)
	 * @see org.littleTeamMachine.srv.controller.Controller#getTasks(java.util.Date, java.util.Date)
	 */
	public Collection<Task> getTasks(Date from, Date to){
		return taskDAO.getTasks(from, to);
	}

	/* (non-Javadoc)
	 * @see org.littleTeamMachine.srv.controller.Controller#refresh(java.lang.Object)
	 */
	public <T extends Root> T refresh(T o){
		return taskDAO.refresh(o);
	}

	/* (non-Javadoc)
	 * @see org.littleTeamMachine.srv.controller.Controller#delete(org.littleTimeMachine.common.modele.Root)
	 */
	public void delete(Root root) {
		taskDAO.delete(root);
	}

	public TaskDAO getTaskDAO() {
		return taskDAO;
	}

	public void setTaskDAO(TaskDAO taskDAO) {
		this.taskDAO = taskDAO;
	}

	public CategorieDAO getCategorieDAO() {
		return categorieDAO;
	}

	public void setCategorieDAO(CategorieDAO categorieDAO) {
		this.categorieDAO = categorieDAO;
	}

	public TaskTypeDAO getTaskTypeDAO() {
		return taskTypeDAO;
	}

	public void setTaskTypeDAO(TaskTypeDAO taskTypeDAO) {
		this.taskTypeDAO = taskTypeDAO;
	}

	public ThemeDAO getThemeDAO() {
		return themeDAO;
	}

	public void setThemeDAO(ThemeDAO themeDAO) {
		this.themeDAO = themeDAO;
	}

	public String getHello() {
		return "hello";
	}

	
}
