package org.littleTeamMachine.srv.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.littleTeamMachine.common.modele.Category;
import org.littleTeamMachine.common.modele.User;
import org.springframework.orm.hibernate3.HibernateCallback;



public class CategorieDAO extends RootDAO {
	
	@SuppressWarnings("unchecked")
	public List<Category> getAllAvailableCategories(final User user){
		
		return (List<Category>)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.getNamedQuery("findAllCategoriesForUser");
				query.setEntity("user", user);
				query.setEntity("group", user.getGroup());
				List<Long> ids = HelperDAO.toLongIDS(user.getGroup().getLinkedGroups(), user.getGroup()); 
				query.setParameterList("linkedGroup", ids);
				return query.list();
			}});

	}
	
	
}
