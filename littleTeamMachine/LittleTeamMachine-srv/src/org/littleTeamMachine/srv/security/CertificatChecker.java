package org.littleTeamMachine.srv.security;

import org.littleTeamMachine.common.modele.User;
import org.littleTeamMachine.common.security.Certificat;

public interface CertificatChecker {
	
	public User getUser(Certificat certificat); 
	

}
