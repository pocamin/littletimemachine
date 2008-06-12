package org.littleTeamMachine.service.jmsReceiver;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;


/**
 * Implementation simple d'une classe set contenant des weakreferences.
 * N'est pas optimisée
 * @author lerouxb
 *
 * @param <T>
 */
public class WeakSet<T> implements Set<T> {
	
	private Set<WeakReference<T>> listWeakReference = new HashSet<WeakReference<T>>();
	private ReferenceQueue<WeakReference<T>> queue = new ReferenceQueue<WeakReference<T>>();
	
	
	
	private void expurge(){
		Reference<? extends WeakReference<T>> reference;
		while ( (reference = queue.poll()) != null ){
			listWeakReference.remove(reference);
		}
	}

	public boolean add(T e) {
		expurge();
		return listWeakReference.add(new WeakReference<T>(e));
	}

	/**
	 * @deprecated : notImplemented
	 */
	public boolean addAll(Collection<? extends T> c) {
		return false;
	}

	public void clear() {
        while (queue.poll() != null);
        listWeakReference = new HashSet<WeakReference<T>>();
	}

	public boolean contains(Object o) {
		if (o == null)
			return false;
		for(WeakReference<T> reference : listWeakReference){
			if (o.equals(reference.get()))
				return true;
		}
		return false;
	}

	/**
	 * @deprecated : notImplemented
	 */
	public boolean containsAll(Collection<?> c) {
		return false;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	@SuppressWarnings("unchecked")
	public Iterator<T> iterator() {
		expurge();
		Collection<?> collectionTmp = 
		CollectionUtils.collect(listWeakReference, new Transformer(){
			public Object transform(Object arg0) {
				return (WeakReference<?>)arg0;
			}});
		return (Iterator<T>) collectionTmp.iterator();
	}

	
	/**
	 * @deprecated
	 * Not implemented
	 */
	public boolean remove(Object o) {
		return false;
	}

	
	/**
	 * @deprecated : notImplemented
	 */
	public boolean removeAll(Collection<?> c) {
		return false;
	}

	/**
	 * @deprecated : notImplemented
	 */
	public boolean retainAll(Collection<?> c) {
		return false;
	}
	public int size() {
		return listWeakReference.size();
	}

	public Object[] toArray() {
		Collection<?> collectionTmp = 
			CollectionUtils.collect(listWeakReference, new Transformer(){
				public Object transform(Object arg0) {
					return (WeakReference<?>)arg0;
				}});
		
		return collectionTmp.toArray();
	}

	@SuppressWarnings({ "unchecked", "hiding" })
	public <T> T[] toArray(T[] a) {
		return (T[]) toArray();
	}
	
	
	

}
