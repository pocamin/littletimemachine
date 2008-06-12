package org.littleTeamMachine.common.constant;

import java.util.HashMap;

public class ConstantVisibility {
	
	public static final int  VISIBILITY_HIDDEN = -1;
	public static final int  VISIBILITY_USER = 0;
	public static final int  VISIBILITY_GROUP = 1;
	public static final int  VISIBILITY_LINKED_GROUP = 2;
	
	private static HashMap<Integer, String> names = new HashMap<Integer, String>(3);
	static{
		names.put(VISIBILITY_HIDDEN, "Caché");
		names.put(VISIBILITY_USER, "Utilisateur");
		names.put(VISIBILITY_GROUP, "Groupe");
		names.put(VISIBILITY_LINKED_GROUP, "Groupes liés");
	}
	
	
	public static String getString(int visibility){
		return names.get(visibility);
	}
	

}
