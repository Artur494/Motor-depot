package by.grsu.by.service.impl;

import java.util.List;

import by.grsu.by.dataaccess.impl.FlightDao;
import by.grsu.by.datamodel.Flight;
import by.grsu.by.service.Service;

public class FlightServiceImpl extends FlightDao implements Service<Flight> {

	public FlightServiceImpl(String rootFolderPath) {
		super(rootFolderPath);
		dao = new FlightDao(rootFolderPath);
	}

	private FlightDao dao;

	@Override
	public void saveOrUpdate(Flight flight) {
		if (flight.getId() == null) {
			dao.saveNew(flight);
		} else {
			dao.update(flight);
		}
	}

	@Override
	public void saveNew(Flight entity) {
		dao.saveNew(entity);
	}

	@Override
	public void update(Flight entity) {
		dao.update(entity);
	}

	@Override
	public Flight get(Long id) {
		return dao.get(id);
	}

	@Override
	public List<Flight> getAll() {
		return dao.getAll();
	}

	@Override
	public void delete(Long id) {
		dao.delete(id);
	}

}
