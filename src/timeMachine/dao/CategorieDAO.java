package timeMachine.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import timeMachine.modele.Categorie;

public class CategorieDAO extends RootDAO {
	
	private static CategorieDAO categorieDAO;
	
	private CategorieDAO(){}
	
	public static synchronized CategorieDAO getInstance(){
		if (categorieDAO == null)
			categorieDAO = new CategorieDAO();
		return categorieDAO;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<Categorie> getAllAvailableCategories(){
		Session session = getSession();
		session.beginTransaction();
		Query query = session.createQuery("from Categorie");
		List toReturn = query.list();
		return toReturn;
	}
	
	
}
