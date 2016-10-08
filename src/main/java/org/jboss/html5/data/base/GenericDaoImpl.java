package org.jboss.html5.data.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public abstract class GenericDaoImpl <T, ID extends Serializable> implements GenericDao<T, ID> {
    private final static String UNIT_NAME = "primary";
    
    @PersistenceContext(unitName = UNIT_NAME, type=PersistenceContextType.TRANSACTION)
    protected EntityManager em;
    
	@SuppressWarnings("unchecked")
	public Class<T> getPersistentClass() {
		Class<T> persistentClass  = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		return persistentClass;
	}    
    
	@Override
	public void Add(T entity) throws Exception {
	    try {
	        em.getTransaction().begin();
	        em.persist(entity);
	        em.getTransaction().commit();
	    } finally {
	        em.close();
	    }
	}

	@Override
	public void Update(T entity) throws Exception {
	    try {
	        em.getTransaction().begin();
	        em.merge(entity);
	        em.getTransaction().commit();
	    } finally {
	        em.close();
	    }
	}

	@Override
	public void Delete(T entity) throws Exception {
	    try {
	        em.getTransaction().begin();
	        em.remove(entity);
	        em.getTransaction().commit();
	    } finally {
	        em.close();
	    }		
	}

	@Override
	public void deleteById(ID id) throws Exception {
		T entity = em.find(getPersistentClass(), id);
		
	    try {
	        em.getTransaction().begin();
	        em.remove(entity);
	        em.getTransaction().commit();
	    } finally {
	        em.close();
	    }
	}	
	
	@Override
	public T getById(ID id) {
        return em.find(getPersistentClass(), id);
	}

	@Override
	public List<T> getListAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> criteria = cb.createQuery(getPersistentClass());
        Root<T> entity = criteria.from(getPersistentClass());
        criteria.select(entity);
        return em.createQuery(criteria).getResultList();
	}
}
