package org.littleTeamMachine.srv.dao;

import java.util.List;

import org.littleTeamMachine.common.modele.TaskType;




public class TaskTypeDAO extends RootDAO{
	
	
	@SuppressWarnings("unchecked")
	public List<TaskType> getAllAvailableTaskType(){
		return getHibernateTemplate().find("from TaskType where finished = false");
	}
	


}
