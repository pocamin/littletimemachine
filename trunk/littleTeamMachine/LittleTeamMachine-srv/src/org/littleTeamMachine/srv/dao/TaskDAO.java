package org.littleTeamMachine.srv.dao;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import org.littleTeamMachine.common.modele.Task;
import org.littleTeamMachine.common.modele.TaskType;
import org.littleTeamMachine.common.modele.User;

public class TaskDAO extends RootDAO {
	
	@SuppressWarnings("unchecked")
	public Collection<Task> getTasks(User user, Date day){
		return getHibernateTemplate().find("from Task where day = ? and user = ?",new Object[]{day,user});
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Task> getTasks(User user,Date day,TaskType taskType){
		if (taskType.getId() != null){
			return getHibernateTemplate().find("from Task where day = ? and taskType = ? and user = ?",new Object[]{day,taskType,user});
		} else return Collections.EMPTY_LIST;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Task> getTasks(User user, Date from, Date to){
		
		
		
		return getHibernateTemplate().find("from Task where day >= ? and day <= ? and user = ?", new Object[]{from,to,user});
	}
	


}
