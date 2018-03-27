package by.grsu.by.service.impl;

import java.util.List;

import by.grsu.by.dataaccess.impl.CarDao;
import by.grsu.by.datamodel.Car;
import by.grsu.by.service.Service;

public class CarServiceImpl extends CarDao implements Service<Car> {

	public CarServiceImpl(String rootFolderPath) {
		super(rootFolderPath);
		dao = new CarDao(rootFolderPath);
	}

	private CarDao dao;

	@Override
	public void saveOrUpdate(Car car) {
		if (car.getId() == null) {
			dao.saveNew(car);
		} else {
			dao.update(car);
		}
	}

	@Override
	public void saveNew(Car entity) {
		dao.saveNew(entity);
	}

	@Override
	public void update(Car entity) {
		dao.update(entity);
	}

	@Override
	public Car get(Long id) {
		return dao.get(id);
	}

	@Override
	public List<Car> getAll() {
		return dao.getAll();
	}

	@Override
	public void delete(Long id) {
		dao.delete(id);
	}

}
