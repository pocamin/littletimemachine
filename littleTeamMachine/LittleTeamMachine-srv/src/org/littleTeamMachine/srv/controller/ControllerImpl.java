package org.littleTeamMachine.srv.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;


import org.littleTeamMachine.common.modele.Category;
import org.littleTeamMachine.common.modele.Root;
import org.littleTeamMachine.common.modele.Task;
import org.littleTeamMachine.common.modele.TaskType;
import org.littleTeamMachine.common.modele.Theme;
import org.littleTeamMachine.common.modele.User;
import org.littleTeamMachine.common.security.Certificat;
import org.littleTeamMachine.common.services.Controller;
import org.littleTeamMachine.common.services.ControllerLocation;
import org.littleTeamMachine.srv.dao.CategorieDAO;
import org.littleTeamMachine.srv.dao.TaskDAO;
import org.littleTeamMachine.srv.dao.TaskTypeDAO;
import org.littleTeamMachine.srv.dao.ThemeDAO;
import org.littleTeamMachine.srv.dao.UserDAO;
import org.littleTeamMachine.srv.security.CertificatChecker;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class ControllerImpl implements Controller  {
	private TaskDAO taskDAO;
	private CategorieDAO categorieDAO;
	private TaskTypeDAO taskTypeDAO;
	private ThemeDAO themeDAO;
	private UserDAO userDAO; 
	private CertificatChecker certificatChecker;  
	
	
	public ControllerImpl() {
		ControllerLocation.CONTROLLER = this;
	}
	
	@Transactional(readOnly=false)
	public void createTasks(Certificat certificat,User user,TaskType taskType, Date startDate, Date endDate, boolean forcasted, boolean dayL,
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
			taskDAO.save(taskType);
		}
		

		
		for (Date date : dates ){
			Task task = new Task();
			task.setTaskType(taskType);
			task.setForcasted(new Boolean(forcasted));
			task.setForecastTimeInMinute(time);
			task.setDay(date);
			task.setUser(user);
			if ( taskDAO.getTasks(user,date, taskType).size() == 0)
				taskDAO.save(task);
		}
		
	}


	@Transactional(readOnly=true)
	public Collection<TaskType> getAvailableTaskTypes(Certificat certificat){
		
		
		return taskTypeDAO.getAllAvailableTaskType(certificatChecker.getUser(certificat));
		
	}
	
	@Transactional(readOnly=true)
	public Collection<Task> getDayTasks(Certificat certificat,Date date) {
		return taskDAO.getTasks(certificatChecker.getUser(certificat),date);
	}

	@Transactional
	public <T extends Root> T save(Certificat certificat,T o){ 
		taskDAO.save(o);
		return o;
		
	}

	@Transactional(readOnly=true)
	public Collection<Category> getAvailableCategories(Certificat certificat) {
		return categorieDAO.getAllAvailableCategories(certificatChecker.getUser(certificat));
	}

	@Transactional(readOnly=true)
	public Collection<Theme> getAvailableThemes(Certificat certificat) {
		return themeDAO.getAllAvailableTheme(certificatChecker.getUser(certificat));
	}
	
	@Transactional(readOnly=true)
	public Collection<Task> getTasks(Certificat certificat,Date from, Date to){
		return taskDAO.getTasks(certificatChecker.getUser(certificat),from, to);
	}

	@Transactional(readOnly=true)
	public <T extends Root> T refresh(Certificat certificat,T o){
		return taskDAO.refresh(o);
	}
	
	@Transactional(readOnly=false)
	public void delete(Certificat certificat,Root root) {
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


	public User getUser(String login, String password) {
		return userDAO.getUser(login,password);
	}


	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}


	public UserDAO getUserDAO() {
		return userDAO;
	}

	@Transactional(readOnly=true)
	public Set<?> lazyLoadCollection(Root root,String property) {
		
		return taskDAO.lazyLoadCollection( root, property);
		
	}

	@Transactional(readOnly=true)
	public List<User> getAssignableUser(Certificat certificat) {
		return getUserDAO().getAssignableUser(certificatChecker.getUser(certificat));
	}


	public void setCertificatChecker(CertificatChecker certificatChecker) {
		this.certificatChecker = certificatChecker;
	}


	public CertificatChecker getCertificatChecker() {
		return certificatChecker;
	}


	
}
