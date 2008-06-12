package org.littleTeamMachine.service;

import org.littleTeamMachine.common.modele.User;
import org.littleTeamMachine.common.security.Certificat;
import org.littleTeamMachine.common.security.SimpleCertificat;

public class LTMContext {

	private static Certificat certificat;
	
	private static User user;

	public static Certificat getCertificat() {
		if (certificat == null)
			certificat = new SimpleCertificat(getUser());
		return certificat;
	}

	public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		LTMContext.user = user;
	}
	
}
