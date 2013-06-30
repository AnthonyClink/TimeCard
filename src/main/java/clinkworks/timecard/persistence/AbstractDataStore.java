package clinkworks.timecard.persistence;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;

import com.google.inject.Inject;

public abstract class AbstractDataStore<P extends Serializable, E extends IEntity<P>> implements IDataStore<P, E>{

	@Inject
	private EntityManager entityManager;
	
	private Class<E> entityClass;
	
	@Override
	public P insert(E entity) {
		getEntityManager().persist(entity);
		return (P)entity.getId();
	}

	@Override
	public E find(P id) {
		return getEntityManager().find(getEntityClass(), id);
	}

	@Override
	public E update(E entity) {
		return getEntityManager().merge(entity);
		
	}

	@Override
	public void delete(E entity) {
		getEntityManager().remove(entity);
		
	}

	@SuppressWarnings("unchecked")
	public Class<E> getEntityClass() {

		if (entityClass != null) {
			return entityClass;  
		}

		Type type = getClass().getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			
			ParameterizedType paramType = (ParameterizedType) type;
			entityClass = (Class<E>) paramType.getActualTypeArguments()[0];
			
		} else {
			
			throw new IllegalArgumentException("Could not guess entity class by reflection");
			
		}
		return entityClass;
	}
	  


	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	
	
	@Override
	@SuppressWarnings("unchecked")
	public List<E> findAll() {
		return this.entityManager.createNamedQuery(getEntityClass().getSimpleName()+".GetAll").getResultList();
	}

}
