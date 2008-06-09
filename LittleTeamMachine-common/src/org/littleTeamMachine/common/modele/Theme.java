package org.littleTeamMachine.common.modele;



public class Theme extends Root {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;


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
