package clinkworks.timecard.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

/**
 * Basic datastore
 * p stands for primary key (your id field)
 * e stands for the entity the datastore returns
 * @author AnthonyJCLink
 *
 * @param <P>
 * @param <E>
 */
public interface IDataStore <P extends Serializable, E extends IEntity<P>>{
	public P insert(E entity);  
	public E find(P id);
	public E update(E entity);
	public void delete(E entity);
	public Class<E> getEntityClass();
	public EntityManager getEntityManager();
	public List<E> findAll();
}
