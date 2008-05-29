package timeMachine.modele;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;



@Entity
public class TaskType extends Root {

	private String name;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Theme theme;
	
	@ManyToOne(fetch=FetchType.EAGER)
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
