package org.littleTeamMachine.common.modele;

public class TaskType extends Root {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	
	private Theme theme;
	
	private Categorie categorie;

	private Boolean finished;
	
	@Override
	public String toString() {
		return name;
	}
	
	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getFinished() {
		return finished;
	}

	public void setFinished(Boolean finished) {
		this.finished = finished;
	}

}
