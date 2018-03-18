package by.grsu.by.dataaccess.impl;

import java.io.Serializable;
import java.util.List;

import by.grsu.by.datamodel.Request;
import by.grsu.by.table.RequestTable;
import by.grsu.by.dataaccess.AbstractDao;


public class RequestDao extends AbstractDao<RequestTable, Request> {

	public RequestDao(final String rootFolderPath) {
		super(rootFolderPath);
	}

	@Override
	public void saveNew(Request newRequest) {
		// set ID
				newRequest.setId(getNextId());
				// get existing data
				final RequestTable requestTable = deserializeFromXml();
				// add new row
				requestTable.getRows().add(newRequest);
				// save data
				serializeToXml(requestTable);
				//
	}

	@Override
	public void update(Request entity) {
		// get existing data
				final RequestTable requestTable = deserializeFromXml();
				// find by ID
				for (final Request row : requestTable.getRows()) {
					if (row.getId().equals(entity.getId())) {
						// found!!!
						// copy data
						row.setStatus(entity.getStatus());
						row.setCondition(entity.getCondition());
						row.setDate(entity.getDate());
						row.setCruisingRange(entity.getCruisingRange());
						row.setBodyType(entity.getBodyType());
						break;
					}
				}
				// save updated table
				serializeToXml(requestTable);
		
	}

	@Override
	public Request get(Serializable id) {
		// get existing data
				final RequestTable requestTable = deserializeFromXml();
				// find by ID
				for (final Request row : requestTable.getRows()) {
					if (row.getId().equals(id)) {
						return row;
					}
				}
				return null;
	}

	@Override
	public List<Request> getAll() {
		// get existing data
				final RequestTable requestTable = deserializeFromXml();
				return requestTable.getRows();
	}

	@Override
	public void delete(Serializable id) {
		// get existing data
				final RequestTable requestTable = deserializeFromXml();
				// find by ID
				Request toBeDeleted = null;
				for (final Request row : requestTable.getRows()) {
					if (row.getId().equals(id)) {
						// found!!!
						toBeDeleted = row;
						break;
					}
				}
				// remove from list
				requestTable.getRows().remove(toBeDeleted);
				// save updated table
				serializeToXml(requestTable);
		
	}

	@Override
	protected Class<RequestTable> getTableClass() {
		return RequestTable.class;
	}

}
