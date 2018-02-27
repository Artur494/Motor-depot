package by.grsu.by.dataaccess.impl;

import java.io.Serializable;
import java.util.List;

import by.grsu.by.datamodel.Flight;
import by.grsu.by.table.FlightTable;
import by.grsu.by.dataaccess.AbstractDao;


public class FlightDao extends AbstractDao<FlightTable, Flight> {

	public FlightDao(final String rootFolderPath) {
		super(rootFolderPath);
	}

	@Override
	public void saveNew(Flight newFlight) {
		// set ID
				newFlight.setId(getNextId());
				// get existing data
				final FlightTable flightTable = deserializeFromXml();
				// add new row
				flightTable.getRows().add(newFlight);
				// save data
				serializeToXml(flightTable);
				//
	}

	@Override
	public void update(Flight entity) {
		// get existing data
				final FlightTable flightTable = deserializeFromXml();
				// find by ID
				for (final Flight row : flightTable.getRows()) {
					if (row.getId().equals(entity.getId())) {
						// found!!!
						// copy data
						row.setDate(entity.getDate());
						row.setStatus(entity.getStatus());
						break;
					}
				}
				// save updated table
				serializeToXml(flightTable);
		
	}

	@Override
	public Flight get(Serializable id) {
		// get existing data
				final FlightTable flightTable = deserializeFromXml();
				// find by ID
				for (final Flight row : flightTable.getRows()) {
					if (row.getId().equals(id)) {
						return row;
					}
				}
				return null;
	}

	@Override
	public List<Flight> getAll() {
		// get existing data
				final FlightTable flightTable = deserializeFromXml();
				return flightTable.getRows();
	}

	@Override
	public void delete(Serializable id) {
		// get existing data
				final FlightTable flightTable = deserializeFromXml();
				// find by ID
				Flight toBeDeleted = null;
				for (final Flight row : flightTable.getRows()) {
					if (row.getId().equals(id)) {
						// found!!!
						toBeDeleted = row;
						break;
					}
				}
				// remove from list
				flightTable.getRows().remove(toBeDeleted);
				// save updated table
				serializeToXml(flightTable);
		
	}

	@Override
	protected Class<FlightTable> getTableClass() {
		return FlightTable.class;
	}

}
