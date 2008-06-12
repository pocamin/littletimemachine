package org.littleTeamMachine.srv.dao;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.littleTeamMachine.common.modele.Root;
import org.littleTeamMachine.common.services.ModificationNotifier;
import org.littleTeamMachine.srv.controller.modificationNotifier.INotifier;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;


public abstract class RootDAO  {
	private HibernateTemplate hibernateTemplate; 
	private SessionFactory factory ;
	private INotifier notifier;
	
	public <T extends Root> T save( T object ){
		getHibernateTemplate().saveOrUpdate(object);
		notifier.sendNotification(object,ModificationNotifier.UPDATE_NOTIFICATION);
		return object;
	}
	
	public <T extends Root> T refresh(T o){
		getHibernateTemplate().refresh(o);
		return o;
	}
	
	public void delete(Root modeleObj){
		getHibernateTemplate().delete(modeleObj);
		notifier.sendNotification(modeleObj,ModificationNotifier.DELETED_NOTIFICATION);
	}

	public SessionFactory getFactory() {
		return factory;
	}

	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public INotifier getNotifier() {
		return notifier;
	}

	public void setNotifier(INotifier notifier) {
		this.notifier = notifier;
	}

	
	public Set<?> lazyLoadCollection(final Root root,final  String property){

		
		return (Set<?>)getHibernateTemplate().execute(new HibernateCallback(){

			@SuppressWarnings("unchecked")
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				
				try {
					
					Root rootRegistered = (Root)session.get(root.getClass(),root.getId());
					
					Method method = root.getClass().getMethod("get" + property.substring(0,1).toUpperCase() + property.substring(1) );
					Set set = (Set)method.invoke(rootRegistered);
					return new HashSet(set);
					
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				}
				
				return null;
			}
			
		});
		
	}
	
}
