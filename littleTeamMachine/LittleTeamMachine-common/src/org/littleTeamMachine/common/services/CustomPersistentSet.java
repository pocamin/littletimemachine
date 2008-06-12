package org.littleTeamMachine.common.services;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.littleTeamMachine.common.modele.Root;

public class CustomPersistentSet<T> implements java.util.Set<T>,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Set<T> innerSet;
	private String name;
	private Root rootObject; 
	
	public CustomPersistentSet(){
		
	}
	
	public CustomPersistentSet(String name, Root rootObject) {
		super();
		this.name = name;
		this.rootObject = rootObject;
	}
	
	

	public boolean add(T e) {
		return getInnerSet().add(e);
	}

	public boolean addAll(Collection<? extends T> c) {
		return getInnerSet().addAll(c);
	}

	public void clear() {
		getInnerSet().clear();
	}

	public boolean contains(Object o) {
		return getInnerSet().contains(o);
	}

	public boolean containsAll(Collection<?> c) {
		return getInnerSet().containsAll(c);
	}

	public boolean isEmpty() {
		return getInnerSet().isEmpty();
	}

	public Iterator<T> iterator() {
		return getInnerSet().iterator();
	}

	public boolean remove(Object o) {
		return getInnerSet().remove(o);
	}

	public boolean removeAll(Collection<?> c) {
		return getInnerSet().removeAll(c);
	}

	public boolean retainAll(Collection<?> c) {
		return getInnerSet().retainAll(c);
	}

	public int size() {
		return getInnerSet().size();
	}

	public Object[] toArray() {
		return getInnerSet().toArray();
	}

	@SuppressWarnings("hiding")
	public <T> T[] toArray(T[] a) {
		return getInnerSet().toArray(a);
	}


	@SuppressWarnings("unchecked")
	public Set<T> getInnerSet() {
		Controller controller = ControllerLocation.CONTROLLER;
		controller.lazyLoadCollection(rootObject, name);
		innerSet = (Set<T>) controller.lazyLoadCollection(rootObject, name);
		try {
			BeanUtils.setProperty(rootObject, name, innerSet);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		//rootObject
		return innerSet;
	}
	
	
	
	

}
