package org.littleTeamMachine.common.modele;

import java.util.Set;

public class Group extends Root {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private Set<Group> linkedGroups;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Group> getLinkedGroups() {
		return linkedGroups;
	}

	public void setLinkedGroups(Set<Group> linkedGroups) {
		this.linkedGroups = linkedGroups;
	}


	
	

}
