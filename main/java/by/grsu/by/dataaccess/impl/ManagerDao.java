package by.grsu.by.dataaccess.impl;

import java.io.Serializable;
import java.util.List;

import by.grsu.by.datamodel.Manager;
import by.grsu.by.table.ManagerTable;
import by.grsu.by.dataaccess.AbstractDao;


public class ManagerDao extends AbstractDao<ManagerTable, Manager> {

	public ManagerDao(final String rootFolderPath) {
		super(rootFolderPath);
	}

	@Override
	public void saveNew(Manager newManager) {
		// set ID
				newManager.setId(getNextId());
				// get existing data
				final ManagerTable managerTable = deserializeFromXml();
				// add new row
				managerTable.getRows().add(newManager);
				// save data
				serializeToXml(managerTable);
				//
	}

	@Override
	public void update(Manager entity) {
		// get existing data
				final ManagerTable managerTable = deserializeFromXml();
				// find by ID
				for (final Manager row : managerTable.getRows()) {
					if (row.getId().equals(entity.getId())) {
						// found!!!
						// copy data
						row.setFirstName(entity.getFirstName());
						row.setLastName(entity.getLastName());
						break;
					}
				}
				// save updated table
				serializeToXml(managerTable);
		
	}

	@Override
	public Manager get(Long id) {
		// get existing data
				final ManagerTable managerTable = deserializeFromXml();
				// find by ID
				for (final Manager row : managerTable.getRows()) {
					if (row.getId().equals(id)) {
						return row;
					}
				}
				return null;
	}

	@Override
	public List<Manager> getAll() {
		// get existing data
				final ManagerTable managerTable = deserializeFromXml();
				return managerTable.getRows();
	}

	@Override
	public void delete(Long id) {
		// get existing data
				final ManagerTable managerTable = deserializeFromXml();
				// find by ID
				Manager toBeDeleted = null;
				for (final Manager row : managerTable.getRows()) {
					if (row.getId().equals(id)) {
						// found!!!
						toBeDeleted = row;
						break;
					}
				}
				// remove from list
				managerTable.getRows().remove(toBeDeleted);
				// save updated table
				serializeToXml(managerTable);
		
	}

	@Override
	protected Class<ManagerTable> getTableClass() {
		return ManagerTable.class;
	}

}
