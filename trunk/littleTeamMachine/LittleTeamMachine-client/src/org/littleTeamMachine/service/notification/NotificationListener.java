package org.littleTeamMachine.service.notification;

import org.littleTeamMachine.common.modele.Root;

/**
 * Attention le notificationListener ne doit faire référence qu'a des objet ne dépendant 
 * pas le l'object cycle de vie ou alors en weakreference
 * @author lerouxb
 *
 */
public abstract class NotificationListener {

	private Root root; 



	@Override
	public int hashCode() {
		return root.getId().hashCode() + root.getClass().hashCode();
	}

	public NotificationListener(Root root){
		this.root = root;
	}

	public abstract void doUpdate();

	public abstract void doDelete();

	public Root getRoot() {
		return root;
	}







}
