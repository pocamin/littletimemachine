package org.littleTeamMachine.srv.dao;


import org.hibernate.SessionFactory;
import org.littleTeamMachine.common.modele.Root;
import org.littleTeamMachine.common.services.ModificationNotifier;
import org.littleTeamMachine.srv.controller.modificationNotifier.Notifier;
import org.springframework.orm.hibernate3.HibernateTemplate;


public abstract class RootDAO  {
	private HibernateTemplate hibernateTemplate; 
	private SessionFactory factory ;
	private Notifier notifier;
	
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

	public Notifier getNotifier() {
		return notifier;
	}

	public void setNotifier(Notifier notifier) {
		this.notifier = notifier;
	}

}
