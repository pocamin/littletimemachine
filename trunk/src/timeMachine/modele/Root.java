package timeMachine.modele;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Transient;

import timeMachine.controller.ModifListener;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Root {
	@Id 
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	
	@Override
	public boolean equals(Object arg0) {
		if (arg0.getClass().equals(this.getClass())) {
			Root root = (Root) arg0;
			if (root.getId() != null){
				return root.getId().equals(getId());
			} 
		}
		return super.equals(arg0);
	}
	
	@Override
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
	
	@Transient
	private List<ModifListener> modifListeners = new ArrayList<ModifListener>(0); 

	public void notifyUpdate(){
		for (ModifListener modifListener : modifListeners){
			modifListener.doUpdate();
		}
	}
	
	public void notifyDelete(){
		for (ModifListener modifListener : modifListeners){
			modifListener.doDelete();
		}
	}

	public void addListener(ModifListener modifListener){
		modifListeners.add(modifListener);
	}
	
}
