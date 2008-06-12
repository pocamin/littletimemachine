package org.littleTeamMachine.srv.controller.modificationNotifier;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.littleTeamMachine.common.modele.Root;
import org.littleTeamMachine.common.services.ModificationNotifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class Notifier implements INotifier {
    private Destination destination;
    private JmsTemplate template;
    
    // Envoie une notification de modification au topic.
    public void sendNotification(final Root root,final int type){
    	
        MessageCreator messageCreator = new MessageCreator() {

			public Message createMessage(Session session) throws JMSException {

				ObjectMessage objectMessage = session.createObjectMessage();
				
				objectMessage.setObject(new ModificationNotifier(root,type));
				
				
				return objectMessage;
			}

        };
    	
    	
        getTemplate().send(getDestination(), messageCreator);
    	
    }
    
    
	public Destination getDestination() {
		return destination;
	}
	public void setDestination(Destination destination) {
		this.destination = destination;
	}
	public JmsTemplate getTemplate() {
		return template;
	}
	public void setTemplate(JmsTemplate template) {
		this.template = template;
	}
    
}
