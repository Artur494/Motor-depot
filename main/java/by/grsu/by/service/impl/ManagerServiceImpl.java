package by.grsu.by.service.impl;

import java.util.List;

import by.grsu.by.dataaccess.impl.ManagerDao;
import by.grsu.by.datamodel.Manager;
import by.grsu.by.service.Service;

public class ManagerServiceImpl extends ManagerDao implements Service<Manager> {

	public ManagerServiceImpl(String rootFolderPath) {
		super(rootFolderPath);
		dao = new ManagerDao(rootFolderPath);
	}

	private ManagerDao dao;

	@Override
	public void saveOrUpdate(Manager manager) {
		if (manager.getId() == null) {
			dao.saveNew(manager);
		} else {
			dao.update(manager);
		}
	}

	@Override
	public void saveNew(Manager entity) {
		dao.saveNew(entity);
	}

	@Override
	public void update(Manager entity) {
		dao.update(entity);
	}

	@Override
	public Manager get(Long id) {
		return dao.get(id);
	}

	@Override
	public List<Manager> getAll() {
		return dao.getAll();
	}

	@Override
	public void delete(Long id) {
		dao.delete(id);
	}

}
