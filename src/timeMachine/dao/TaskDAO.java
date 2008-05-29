package timeMachine.dao;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import timeMachine.modele.Task;
import timeMachine.modele.TaskType;

public class TaskDAO extends RootDAO {
	
	private static TaskDAO taskDAO;
	
	private TaskDAO(){}
	
	public static synchronized TaskDAO getInstance(){
		if (taskDAO == null)
			taskDAO = new TaskDAO();
		return taskDAO;
	}
	
	
	@SuppressWarnings("unchecked")
	public Collection<Task> getTasks(Date day){
		Session session = getSession();
		session.beginTransaction();
		Query query = session.createQuery("from Task where day = :day");
		query.setDate("day", day);
		List toReturn = query.list();
		return toReturn;
		
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Task> getTasks(Date day,TaskType taskType){
		if (taskType.getId() != null){
			Session session = getSession();
			session.beginTransaction();
			Query query = session.createQuery("from Task where day = :day and taskType = :taskType");
			query.setDate("day", day);
			query.setEntity("taskType", taskType);
			List toReturn = query.list();
			return toReturn;
		} else return Collections.EMPTY_LIST;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Task> getTasks(Date from, Date to){

		Session session = getSession();
		session.beginTransaction();
		Query query = session.createQuery("from Task where day >= :from and day <= :to");
		query.setDate("from", from);
		query.setDate("to", to);
		List toReturn = query.list();
		return toReturn;
		
	}
	


}
