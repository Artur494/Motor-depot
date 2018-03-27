package by.grsu.by.dataaccess.impl;

import java.io.Serializable;
import java.util.List;

import by.grsu.by.dataaccess.AbstractDao;
import by.grsu.by.datamodel.UserProfile;
import by.grsu.by.table.UserProfileTable;

public class UserProfileDao extends AbstractDao<UserProfileTable, UserProfile> implements Serializable {

	public UserProfileDao(final String rootFolderPath) {
		super(rootFolderPath);
	}

	@Override
	public void saveNew(UserProfile newUserProfile) {
		// set ID
		newUserProfile.setId(newUserProfile.getCredentials().getId());// getNextId());
		// get existing data
		final UserProfileTable userProfileTable = deserializeFromXml();
		// add new row
		userProfileTable.getRows().add(newUserProfile);
		// save data
		serializeToXml(userProfileTable);
		//
	}

	@Override
	public void update(UserProfile entity) {
		// get existing data
		final UserProfileTable userProfileTable = deserializeFromXml();
		// find by ID
		for (final UserProfile row : userProfileTable.getRows()) {
			if (row.getId().equals(entity.getId())) {
				// found!!!
				// copy data
				row.setCreated(entity.getCreated());
				row.setCredentials(entity.getCredentials());
				row.setFirstName(entity.getFirstName());
				row.setId(entity.getId());
				row.setLastName(entity.getLastName());
				break;
			}
		}
		// save updated table
		serializeToXml(userProfileTable);
	}

	@Override
	public UserProfile get(Long id) {
		// get existing data
		final UserProfileTable userProfileTable = deserializeFromXml();
		// find by ID
		for (final UserProfile row : userProfileTable.getRows()) {
			if (row.getId().equals(id)) {
				return row;
			}
		}
		return null;
	}

	@Override
	public List<UserProfile> getAll() {
		// get existing data
		final UserProfileTable userProfileTable = deserializeFromXml();
		return userProfileTable.getRows();
	}

	@Override
	public void delete(Long id) {
		// get existing data
		final UserProfileTable userProfileTable = deserializeFromXml();
		// find by ID
		UserProfile toBeDeleted = null;
		for (final UserProfile row : userProfileTable.getRows()) {
			if (row.getId().equals(id)) {
				// found!!!
				toBeDeleted = row;
				break;
			}
		}
		// remove from list
		userProfileTable.getRows().remove(toBeDeleted);
		// save updated table
		serializeToXml(userProfileTable);
	}

	@Override
	protected Class<UserProfileTable> getTableClass() {
		return UserProfileTable.class;
	}

}