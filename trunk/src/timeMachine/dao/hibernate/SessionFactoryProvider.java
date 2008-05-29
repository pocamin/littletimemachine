package timeMachine.dao.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public abstract class SessionFactoryProvider {
	
	static SessionFactory factory;
	
	
	
	public synchronized static SessionFactory  getInstance(){
		if (factory == null)
			factory = new AnnotationConfiguration().configure().buildSessionFactory();
		return factory;
	}

}
