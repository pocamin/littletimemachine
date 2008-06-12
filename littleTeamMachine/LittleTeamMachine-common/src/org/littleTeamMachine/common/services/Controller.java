package org.littleTeamMachine.common.services;

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

public interface Controller {

	public abstract void createTasks(Certificat certificat, User user, TaskType taskType, Date startDate,
			Date endDate, boolean forcasted, boolean dayL, boolean dayMa,
			boolean dayMe, boolean dayJ, boolean dayV, long time);

	
	public abstract Set<?> lazyLoadCollection(Root root,String property);
	
	public abstract Collection<TaskType> getAvailableTaskTypes(Certificat certificat);

	public abstract Collection<Task> getDayTasks(Certificat certificat,Date date);

	public abstract <T extends Root> T save(Certificat certificat,T o);
	
	public abstract Collection<Category> getAvailableCategories(Certificat certificat);

	public abstract Collection<Theme> getAvailableThemes(Certificat certificat);

	public abstract Collection<Task> getTasks(Certificat certificat,Date from, Date to);

	public abstract <T extends Root> T refresh(Certificat certificat,T o);

	public abstract void delete(Certificat certificat,Root root);

	public abstract User getUser(String text, String text2);

	public abstract List<User> getAssignableUser(Certificat certificat);


}