package by.grsu.by.dataaccess.impl;

import java.io.Serializable;
import java.util.List;

import by.grsu.by.datamodel.Driver;
import by.grsu.by.table.DriverTable;
import by.grsu.by.dataaccess.AbstractDao;


public class DriverDao extends AbstractDao<DriverTable, Driver> {

	public DriverDao(final String rootFolderPath) {
		super(rootFolderPath);
	}

	@Override
	public void saveNew(Driver newDriver) {
		// set ID
				newDriver.setId(getNextId());
				// get existing data
				final DriverTable driverTable = deserializeFromXml();
				// add new row
				driverTable.getRows().add(newDriver);
				// save data
				serializeToXml(driverTable);
				//
	}

	@Override
	public void update(Driver entity) {
		// get existing data
				final DriverTable driverTable = deserializeFromXml();
				// find by ID
				for (final Driver row : driverTable.getRows()) {
					if (row.getId().equals(entity.getId())) {
						// found!!!
						// copy data
						row.setCar(entity.getCar());
						row.setName(entity.getName());
						break;
					}
				}
				// save updated table
				serializeToXml(driverTable);
		
	}

	@Override
	public Driver get(Serializable id) {
		// get existing data
				final DriverTable driverTable = deserializeFromXml();
				// find by ID
				for (final Driver row : driverTable.getRows()) {
					if (row.getId().equals(id)) {
						return row;
					}
				}
				return null;
	}

	@Override
	public List<Driver> getAll() {
		// get existing data
				final DriverTable driverTable = deserializeFromXml();
				return driverTable.getRows();
	}

	@Override
	public void delete(Serializable id) {
		// get existing data
				final DriverTable driverTable = deserializeFromXml();
				// find by ID
				Driver toBeDeleted = null;
				for (final Driver row : driverTable.getRows()) {
					if (row.getId().equals(id)) {
						// found!!!
						toBeDeleted = row;
						break;
					}
				}
				// remove from list
				driverTable.getRows().remove(toBeDeleted);
				// save updated table
				serializeToXml(driverTable);
		
	}

	@Override
	protected Class<DriverTable> getTableClass() {
		return DriverTable.class;
	}

}
