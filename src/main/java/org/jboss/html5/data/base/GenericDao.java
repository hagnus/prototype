package org.jboss.html5.data.base;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, ID extends Serializable> {
	void Add(T entity) throws Exception;
	void Update(T entity) throws Exception;
	void Delete(T entity) throws Exception;
	void deleteById(ID id) throws Exception;
	T getById(ID id);
	List<T> getListAll();	
}
