package org.littleTeamMachine.common.security;

import org.littleTeamMachine.common.modele.User;

/**
 * Basic a redéfinir plus tard
 * @author lerouxb
 *
 */
public class SimpleCertificat implements Certificat   {
	
	private static final long serialVersionUID = 1L;
	
	
	Long userID ;
	int password;
	
	
	public SimpleCertificat(User user){
		userID = user.getId();
		password = user.getPassword().hashCode();
	}
	
	public Long getUserID() {
		return userID;
	}


	public void setUserID(Long userID) {
		this.userID = userID;
	}


	public int getPassword() {
		return password;
	}


	public void setPassword(int password) {
		this.password = password;
	}
	

}
