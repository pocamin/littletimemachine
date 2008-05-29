package timeMachine.modele;

import javax.persistence.Entity;

@Entity
public class Theme extends Root {

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
