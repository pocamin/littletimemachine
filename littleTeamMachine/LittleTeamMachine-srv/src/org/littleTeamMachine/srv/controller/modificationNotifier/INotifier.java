package org.littleTeamMachine.srv.controller.modificationNotifier;

import org.littleTeamMachine.common.modele.Root;

public interface INotifier {

	/**
	 * Envoie une notification de modification au topic.
	 * @param root
	 * @param type
	 */
	public abstract void sendNotification(final Root root, final int type);

}