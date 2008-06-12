package org.littleTeamMachine.common.modele;

import org.littleTeamMachine.common.constant.ConstantVisibility;



public class Theme extends Root {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;

	private int visibility = ConstantVisibility.VISIBILITY_GROUP;

	private Group group; 
	
	private User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}
	
	public int getVisibility() {
		return visibility;
	}

	public void setVisibility(int visibility) {
		this.visibility = visibility;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name.length() < 3 )
			throw new RuntimeException("theme name too short");
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
	
}
