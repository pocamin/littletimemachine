package org.littleTeamMachine.srv.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.littleTeamMachine.common.modele.User;
import org.springframework.orm.hibernate3.HibernateCallback;

public class UserDAO extends RootDAO {
	
	public User getUser(Long id){
		return (User)getHibernateTemplate().get(User.class, id);
	}

	@SuppressWarnings("unchecked")
	public User getUser(String login, String password) {
		List<User> users = (List<User>)getHibernateTemplate().find("from User where login = ? and password = ?",new Object[]{login,password});
		if (users != null && users.size() == 1)
			return users.get(0);
		else return null;
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUser() {
		return (List<User>)getHibernateTemplate().find("from User");
	}
	@SuppressWarnings("unchecked")
	public List<User> getAssignableUser(final User user) {
		if (user.getTaskCreate() == User.ALLOW_GROUP_CREATE){
			return (List<User>)getHibernateTemplate().find("from User where group = ? ",new Object[]{user.getGroup()});
		} else if (user.getTaskCreate() == User.ALLOW_LINKED_GROUP_CREATE){
			return (List<User>)getHibernateTemplate().execute(new HibernateCallback(){

				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					
					
					user.getGroup().getLinkedGroups().add(user.getGroup());
					
					return session.createCriteria(User.class)
						.add(
								Restrictions.or(
										Restrictions.eq("group", user.getGroup()),
										Restrictions.in("group", user.getGroup().getLinkedGroups())
										)	
						).list();
				}});
			
		}
		
		
		return null;
	}
	

}
