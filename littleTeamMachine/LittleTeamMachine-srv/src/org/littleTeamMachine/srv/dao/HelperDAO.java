package org.littleTeamMachine.srv.dao;

import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.littleTeamMachine.common.modele.Root;



public class HelperDAO {
	
	
	@SuppressWarnings("unchecked")
	public static List<Long> toLongIDS(Collection rootsList,Root... otherRoots  ){
		
		List<Long> toReturn = (List<Long>)CollectionUtils.collect(rootsList, new Transformer(){
			public Object transform(Object root) {
				return ((Root)root).getId();
			}});
		
		for (Root root : otherRoots){
			toReturn.add(root.getId());
		}
		
		return toReturn;
		
	}
	

}
