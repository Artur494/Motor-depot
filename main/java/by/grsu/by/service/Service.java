package by.grsu.by.service;

import java.util.List;

public interface Service<T> {

	void saveNew(T entity);

	void update(T entity);

	T get(Long id);

	List<T> getAll();

	void delete(Long id);

	void saveOrUpdate(T entity);

}
