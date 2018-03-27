package by.grsu.by.service.impl;

import java.util.List;

import by.grsu.by.dataaccess.impl.DriverDao;
import by.grsu.by.datamodel.Driver;
import by.grsu.by.service.Service;

public class DriverServiceImpl extends DriverDao implements Service<Driver> {

	public DriverServiceImpl(String rootFolderPath) {
		super(rootFolderPath);
		dao = new DriverDao(rootFolderPath);
	}

	private DriverDao dao;

	@Override
	public void saveOrUpdate(Driver driver) {
		if (driver.getId() == null) {
			dao.saveNew(driver);
		} else {
			dao.update(driver);
		}
	}

	@Override
	public void saveNew(Driver entity) {
		dao.saveNew(entity);
	}

	@Override
	public void update(Driver entity) {
		dao.update(entity);
	}

	@Override
	public Driver get(Long id) {
		return dao.get(id);
	}

	@Override
	public List<Driver> getAll() {
		return dao.getAll();
	}

	@Override
	public void delete(Long id) {
		dao.delete(id);
	}

}
