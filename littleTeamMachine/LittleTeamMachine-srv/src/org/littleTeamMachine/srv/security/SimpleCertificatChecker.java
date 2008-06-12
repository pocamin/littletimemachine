package org.littleTeamMachine.srv.security;

import org.littleTeamMachine.common.modele.User;
import org.littleTeamMachine.common.security.Certificat;
import org.littleTeamMachine.common.security.SimpleCertificat;
import org.littleTeamMachine.srv.dao.UserDAO;

public class SimpleCertificatChecker implements CertificatChecker {

	private UserDAO userDAO;


	public User getUser(Certificat c) {
		if (c instanceof SimpleCertificat){
			SimpleCertificat certificat = (SimpleCertificat)c; 
			User user = userDAO.getUser(certificat.getUserID());
			if (user.getPassword().hashCode() == certificat.getPassword() ){
				return user;
			} 
		}
		return null;
	}


	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}


	public UserDAO getUserDAO() {
		return userDAO;
	}
	

	

}
