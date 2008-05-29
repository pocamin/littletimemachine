package timeMachine.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import timeMachine.dao.hibernate.SessionFactoryProvider;
import timeMachine.modele.Root;

public abstract class RootDAO {
	
	private static Session session ;
	
	public <T extends Root> T save( T object ){
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		if (object.getId() == null)
			session.save(object);
		else {
			session.update(object);
			object.notifyUpdate();
		}
		transaction.commit();
		return object; 
	}
	
	public void refresh(Object o){
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		session.refresh(o);
		transaction.commit();
	}
	
	
	public void delete(Root modeleObj){
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		session.delete(modeleObj);
		modeleObj.notifyDelete();
		transaction.commit();
	}
	
	
	public Session getSession(){
		if (session == null)
			session = SessionFactoryProvider.getInstance().openSession();
		return session;
		
	}

}
