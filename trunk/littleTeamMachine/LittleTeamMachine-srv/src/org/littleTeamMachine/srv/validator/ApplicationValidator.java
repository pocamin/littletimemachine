package org.littleTeamMachine.srv.validator;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.littleTeamMachine.common.modele.Group;
import org.littleTeamMachine.common.modele.User;
import org.littleTeamMachine.srv.dao.UserDAO;


/**
 * This class perform at startup nécessary opération in order to have the application Run
 * @author lerouxb
 *
 */
public class ApplicationValidator {
	
	private static final Log log = LogFactory.getLog(ApplicationValidator.class);
	
	
	UserDAO userDAO = new UserDAO();
	
	
	public void validate(){
		generateAdminUser();
	}

	private void generateAdminUser() {
		List<User> users = userDAO.getAllUser();
		if (users.size() == 0){
			log.info("No user in database create user admin/admin");
			Group group = new Group();
			group.setName("admin");
			
			
			User user = new User();
			user.setGroup(group);
			user.setLogin("admin");
			user.setPassword("admin");
			
			userDAO.save(user);
		}
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	
	
	
	
	
	

}
