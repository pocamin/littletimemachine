package org.littleTeamMachine.common.modele;

import java.io.Serializable;

public abstract class Root implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	
	public boolean equals(Object arg0) {
		if (arg0.getClass().equals(this.getClass())) {
			Root root = (Root) arg0;
			if (root.getId() != null){
				return root.getId().equals(getId());
			} 
		}
		return super.equals(arg0);
	}
	
	public int hashCode() {
		if (id != null)
			return id.hashCode();
		else return super.hashCode(); 
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
