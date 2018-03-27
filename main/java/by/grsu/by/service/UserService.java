package by.grsu.by.service;

import java.util.Collection;

import by.grsu.by.datamodel.UserCredentials;
import by.grsu.by.datamodel.UserProfile;

public interface UserService {

	void register(UserProfile profile, UserCredentials userCredentials);

	UserProfile getProfile(Long id);

	UserCredentials getCredentials(Long id);

	UserCredentials getByNameAndPassword(String userName, String password);

	Collection<? extends String> resolveRoles(Long id);
}
