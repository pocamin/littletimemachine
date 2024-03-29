package org.littleTeamMachine.srv.dao;

import java.sql.SQLException;
import java.util.List;



import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.littleTeamMachine.common.modele.TaskType;
import org.littleTeamMachine.common.modele.User;
import org.springframework.orm.hibernate3.HibernateCallback;


public class TaskTypeDAO extends RootDAO{
	
	@SuppressWarnings("unchecked")
	public List<TaskType> getAllAvailableTaskType(final User user){
		
		return (List<TaskType>)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.getNamedQuery("findAllTaskTypeForUser");
				query.setEntity("user", user);
				query.setEntity("group", user.getGroup());
				List<Long> ids = HelperDAO.toLongIDS(user.getGroup().getLinkedGroups(), user.getGroup()); 
				query.setParameterList("linkedGroup",ids);
				return query.list();
			}});
		
	}
	


}
