package by.grsu.by.dataaccess.impl;

import java.io.Serializable;
import java.util.List;

import by.grsu.by.dataaccess.AbstractDao;
import by.grsu.by.datamodel.Car;
import by.grsu.by.table.CarTable;


public class CarDao extends AbstractDao<CarTable, Car> implements Serializable {

	public CarDao(final String rootFolderPath) {
		super(rootFolderPath);
	}

	@Override
	public void saveNew(Car newCar) {
		// set ID
				newCar.setId(getNextId());
				// get existing data
				final CarTable carTable = deserializeFromXml();
				// add new row
				carTable.getRows().add(newCar);
				// save data
				serializeToXml(carTable);
				//
	}

	@Override
	public void update(Car entity) {
		// get existing data
				final CarTable carTable = deserializeFromXml();
				// find by ID
				for (final Car row : carTable.getRows()) {
					if (row.getId().equals(entity.getId())) {
						// found!!!
						// copy data
						row.setCondition(entity.getCondition());
						row.setCarModel(entity.getCarModel());
						row.setBodyType(entity.getBodyType());
						row.setCruisingRange(entity.getCruisingRange());
						row.setNumberCar(entity.getNumberCar());
						break;
					}
				}
				// save updated table
				serializeToXml(carTable);
		
	}

	@Override
	public Car get(Long id) {
		// get existing data
				final CarTable carTable = deserializeFromXml();
				// find by ID
				for (final Car row : carTable.getRows()) {
					if (row.getId().equals(id)) {
						return row;
					}
				}
				return null;
	}

	@Override
	public List<Car> getAll() {
		// get existing data
				final CarTable carTable = deserializeFromXml();
				return carTable.getRows();
	}

	@Override
	public void delete(Long id) {
		// get existing data
				final CarTable carTable = deserializeFromXml();
				// find by ID
				Car toBeDeleted = null;
				for (final Car row : carTable.getRows()) {
					if (row.getId().equals(id)) {
						// found!!!
						toBeDeleted = row;
						break;
					}
				}
				// remove from list
				carTable.getRows().remove(toBeDeleted);
				// save updated table
				serializeToXml(carTable);
		
	}

	@Override
	protected Class<CarTable> getTableClass() {
		return CarTable.class;
	}

}
