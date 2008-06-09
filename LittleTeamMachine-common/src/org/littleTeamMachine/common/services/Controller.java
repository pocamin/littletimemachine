package org.littleTeamMachine.common.services;

import java.util.Collection;
import java.util.Date;

import org.littleTeamMachine.common.modele.Categorie;
import org.littleTeamMachine.common.modele.Root;
import org.littleTeamMachine.common.modele.Task;
import org.littleTeamMachine.common.modele.TaskType;
import org.littleTeamMachine.common.modele.Theme;

public interface Controller {

	public abstract void createTasks(TaskType taskType, Date startDate,
			Date endDate, boolean forcasted, boolean dayL, boolean dayMa,
			boolean dayMe, boolean dayJ, boolean dayV, long time);

	public abstract Collection<TaskType> getAvailableTaskTypes();

	public abstract Collection<Task> getTasks(Date date);

	public abstract <T extends Root> T save(T o);
	
	public abstract Collection<Categorie> getAvailableCategories();

	public abstract Collection<Theme> getAvailableThemes();

	public abstract Collection<Task> getTasks(Date from, Date to);

	public abstract <T extends Root> T refresh(T o);

	public abstract void delete(Root root);

	public abstract String getHello();

}