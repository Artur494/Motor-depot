package by.grsu.by.dataaccess.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import by.grsu.by.dataaccess.AbstractDao;
import by.grsu.by.datamodel.UserCredentials;
import by.grsu.by.table.UserCredentialsTable;

public class UserCredentialsDao extends AbstractDao<UserCredentialsTable, UserCredentials> implements Serializable {

	public UserCredentialsDao(final String rootFolderPath) {
		super(rootFolderPath);
	}

	@Override
	public void saveNew(UserCredentials newUserCredentials) {
		// set ID
		newUserCredentials.setId(getNextId());
		// get existing data
		final UserCredentialsTable userCredentialsTable = deserializeFromXml();
		// add new row
		userCredentialsTable.getRows().add(newUserCredentials);
		// save data
		serializeToXml(userCredentialsTable);
		//
	}

	@Override
	public void update(UserCredentials entity) {
		// get existing data
		final UserCredentialsTable userCredentialsTable = deserializeFromXml();
		// find by ID
		for (final UserCredentials row : userCredentialsTable.getRows()) {
			if (row.getId().equals(entity.getId())) {
				// found!!!
				// copy data
				row.setEmail(entity.getEmail());
				row.setPassword(entity.getPassword());
				row.setRole(entity.getRole());
				break;
			}
		}
		// save updated table
		serializeToXml(userCredentialsTable);
	}

	@Override
	public UserCredentials get(Long id) {
		// get existing data
		final UserCredentialsTable userCredentialsTable = deserializeFromXml();
		// find by ID
		for (final UserCredentials row : userCredentialsTable.getRows()) {
			if (row.getId().equals(id)) {
				return row;
			}
		}
		return null;
	}

	@Override
	public List<UserCredentials> getAll() {
		// get existing data
		final UserCredentialsTable userCredentialsTable = deserializeFromXml();
		return userCredentialsTable.getRows();
	}

	@Override
	public void delete(Long id) {
		// get existing data
		final UserCredentialsTable userCredentialsTable = deserializeFromXml();
		// find by ID
		UserCredentials toBeDeleted = null;
		for (final UserCredentials row : userCredentialsTable.getRows()) {
			if (row.getId().equals(id)) {
				// found!!!
				toBeDeleted = row;
				break;
			}
		}
		// remove from list
		userCredentialsTable.getRows().remove(toBeDeleted);
		// save updated table
		serializeToXml(userCredentialsTable);
	}

	@Override
	protected Class<UserCredentialsTable> getTableClass() {
		return UserCredentialsTable.class;
	}

	public UserCredentials find(String userName, String password) {
		
		List<UserCredentials> userCredentialsList = getAll();
		List<UserCredentials> allItems = new ArrayList<UserCredentials>();
		
		for (UserCredentials userCredentials : userCredentialsList) {
			boolean isEmailEqual = userCredentials.getEmail().equals(userName);
			boolean isPasswordEqual = userCredentials.getPassword().equals(password);
			if (isEmailEqual && isPasswordEqual) {
				allItems.add(userCredentials);
			}
		}
		if (allItems.isEmpty()) {
			return null;
		} else if (allItems.size() == 1) {
			return allItems.get(0);
		} else {
			throw new IllegalArgumentException("more than 1 user found ");
		}
	}

}
