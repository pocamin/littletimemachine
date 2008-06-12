package org.littleTeamMachine.srv.controller.Invoker;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import org.hibernate.collection.PersistentSet;
import org.littleTeamMachine.common.modele.Root;
import org.littleTeamMachine.common.services.CustomPersistentSet;
import org.springframework.util.StringUtils;



public class CustomObjectOutputStream extends ObjectOutputStream{

	
	
	public CustomObjectOutputStream(OutputStream out) throws IOException {
		super(out);
		enableReplaceObject(true);
	}

	@Override
	@SuppressWarnings("unchecked")
	protected Object replaceObject(Object obj) throws IOException {
		if (obj instanceof PersistentSet){
			PersistentSet persistentSet = (PersistentSet)obj;
			String role = persistentSet.getRole();
			String[] roleParts = StringUtils.delimitedListToStringArray(role, ".");
			
			return super.replaceObject(new CustomPersistentSet(roleParts[roleParts.length -1],(Root)persistentSet.getOwner()) );
		}
		
		
		return super.replaceObject(obj);
	}
	
	

}
