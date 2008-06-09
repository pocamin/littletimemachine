package org.littleTeamMachine.srv.dao;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import org.littleTeamMachine.common.modele.Task;
import org.littleTeamMachine.common.modele.TaskType;

public class TaskDAO extends RootDAO {
	
	@SuppressWarnings("unchecked")
	public Collection<Task> getTasks(Date day){
		return getHibernateTemplate().find("from Task where day = ?",day);
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Task> getTasks(Date day,TaskType taskType){
		if (taskType.getId() != null){
			return getHibernateTemplate().find("from Task where day = ? and taskType = ?",new Object[]{day,taskType});
		} else return Collections.EMPTY_LIST;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Task> getTasks(Date from, Date to){
		return getHibernateTemplate().find("from Task where day >= ? and day <= ?",new Object[]{from,to});
	}
	


}
