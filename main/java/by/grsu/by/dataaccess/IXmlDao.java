package by.grsu.by.dataaccess;

import java.io.Serializable;
import java.util.List;

public interface IXmlDao<E> {

	void saveNew(E entity);

	void update(E entity);

	E get(Serializable id);

	List<E> getAll();

	void delete(Serializable id);
}
