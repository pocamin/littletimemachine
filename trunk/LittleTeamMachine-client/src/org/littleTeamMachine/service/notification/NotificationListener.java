package org.littleTeamMachine.service.notification;

import org.littleTeamMachine.common.modele.Root;
import org.littleTeamMachine.common.services.ModificationNotifier;

public abstract class NotificationListener {

	private Root root; 



	@Override
	public int hashCode() {
		return root.getId().hashCode() + root.getClass().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof NotificationListener){
			NotificationListener notification = (NotificationListener)obj;
			return notification.root.equals(root); 
		} else if (obj instanceof ModificationNotifier ){
			ModificationNotifier notifier = (ModificationNotifier)obj;
			return notifier.getId().equals(root.getId()) && notifier.getObjectClass().equals(root.getClass());
		}
		return super.equals(obj);
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
