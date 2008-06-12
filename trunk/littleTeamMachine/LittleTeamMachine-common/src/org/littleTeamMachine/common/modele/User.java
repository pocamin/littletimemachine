package org.littleTeamMachine.common.modele;

public class User extends Root {

	
	public static final int ALLOW_CREATE =0; 
	public static final int ALLOW_GROUP_CREATE =1;
	public static final int ALLOW_LINKED_GROUP_CREATE =2;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Group group;
	private String login;
	private String password;
	private boolean adminGroup;

	private int taskCreate = ALLOW_CREATE;
	private int taskTypeCreate = ALLOW_GROUP_CREATE;
	private int categoryCreate = ALLOW_GROUP_CREATE;
	private int themeCreate =  ALLOW_GROUP_CREATE;
	
	
	@Override
	public String toString() {
		return login + "(" + group.getName() + ")";
	}
	
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isAdminGroup() {
		return adminGroup;
	}
	public void setAdminGroup(boolean adminGroup) {
		this.adminGroup = adminGroup;
	}
	public int getTaskCreate() {
		return taskCreate;
	}
	public void setTaskCreate(int taskCreate) {
		this.taskCreate = taskCreate;
	}
	public int getTaskTypeCreate() {
		return taskTypeCreate;
	}
	public void setTaskTypeCreate(int taskTypeCreate) {
		this.taskTypeCreate = taskTypeCreate;
	}
	public int getCategoryCreate() {
		return categoryCreate;
	}
	public void setCategoryCreate(int categoryCreate) {
		this.categoryCreate = categoryCreate;
	}
	public int getThemeCreate() {
		return themeCreate;
	}
	public void setThemeCreate(int themeCreate) {
		this.themeCreate = themeCreate;
	}
	
	
	
	
	
	
}
