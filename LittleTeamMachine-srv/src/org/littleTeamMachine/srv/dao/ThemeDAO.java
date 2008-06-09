package org.littleTeamMachine.srv.dao;

import java.util.List;

import org.littleTeamMachine.common.modele.Theme;


public class ThemeDAO extends RootDAO{
	
	@SuppressWarnings("unchecked")
	public List<Theme> getAllAvailableTheme(){
		return getHibernateTemplate().find("from Theme");
	}
	
	
}
