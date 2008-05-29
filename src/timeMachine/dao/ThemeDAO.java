package timeMachine.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import timeMachine.modele.Theme;

public class ThemeDAO extends RootDAO{
	
	private static ThemeDAO themeDAO ;
	
	private ThemeDAO(){}
	
	public static synchronized ThemeDAO getInstance(){
		if (themeDAO == null)
			themeDAO = new ThemeDAO();
		return themeDAO;
	}
	
	@SuppressWarnings("unchecked")
	public List<Theme> getAllAvailableTheme(){
		Session session = getSession();
		session.beginTransaction();
		Query query = session.createQuery("from Theme");
		List toReturn = query.list();
		return toReturn;
	}
	
	
}
