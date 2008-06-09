package org.littleTeamMachine.srv.dao;

import java.util.List;

import org.littleTeamMachine.common.modele.Categorie;



public class CategorieDAO extends RootDAO {
	
	@SuppressWarnings("unchecked")
	public List<Categorie> getAllAvailableCategories(){
		return getHibernateTemplate().find("from Categorie");
	}
	
	
}
