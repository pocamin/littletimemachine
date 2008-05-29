package timeMachine.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import timeMachine.modele.TaskType;


public class TaskTypeDAO extends RootDAO{
	
	private static TaskTypeDAO taskTypeDAO;
	
	private TaskTypeDAO(){}
	
	public static synchronized TaskTypeDAO getInstance(){
		if (taskTypeDAO == null)
			taskTypeDAO = new TaskTypeDAO();
		return taskTypeDAO;
	}
	
	@SuppressWarnings("unchecked")
	public List<TaskType> getAllAvailableTaskType(){
		Session session = getSession();
		session.beginTransaction();
		Query query = session.createQuery("from TaskType where finished = false");
		List toReturn = query.list();
		return toReturn;
	}
	


}
