package org.littleTeamMachine.common.services;

import java.io.Serializable;

import org.littleTeamMachine.common.modele.Root;

public class ModificationNotifier implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final int DELETED_NOTIFICATION = 1;
	public static final int UPDATE_NOTIFICATION = 0;
	
	private Long id;
	
	@SuppressWarnings("unchecked")
	private Class objectClass;


	private int type;

	public ModificationNotifier(){
		
	}
	
	public ModificationNotifier(Root root, int type) {
		id = root.getId();
		objectClass = root.getClass();
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@SuppressWarnings("unchecked")
	public Class getObjectClass() {
		return objectClass;
	}

	@SuppressWarnings("unchecked")
	public void setObjectClass(Class objectClass) {
		this.objectClass = objectClass;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}


}
