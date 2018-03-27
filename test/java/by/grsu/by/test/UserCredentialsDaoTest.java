package by.grsu.by.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import by.grsu.by.dataaccess.impl.UserCredentialsDao;
import by.grsu.by.datamodel.UserCredentials;
import by.grsu.by.datamodel.UserRole;
import junit.framework.Assert;

public class UserCredentialsDaoTest {
	private static final String TEST_XML_FOLDER = "testXmlFolder";
	private static UserCredentialsDao userCredentialsDao;

	@BeforeClass
	public static void createDao() {
		userCredentialsDao = new UserCredentialsDao(TEST_XML_FOLDER);
	}

	@AfterClass
	public static void deleteTestXmlData() {
		// write code to clean up test results from FS

	}

	@Test
	public void testAdd() {
		System.out.println("Start 'save' test for user credentials");
		final UserCredentials newCredentials = saveNewUserCredentials();
		Assert.assertNotNull(userCredentialsDao.get(newCredentials.getId()));
	}

	@Test
	public void testDelete() {
		System.out.println("Start 'delete' test for credentials");
		final UserCredentials newCredentials = saveNewUserCredentials();
		userCredentialsDao.delete(newCredentials.getId());
		Assert.assertNull(userCredentialsDao.get(newCredentials.getId()));
	}

	@Test
	public void testGetAll() {
		System.out.println("Start 'getAll' test for credentials");
		final int initialRowsCount = userCredentialsDao.getAll().size();
		saveNewUserCredentials();
		Assert.assertEquals(userCredentialsDao.getAll().size(), initialRowsCount + 1);
	}

	private UserCredentials saveNewUserCredentials() {
		final UserCredentials newCredentials = new UserCredentials();
		newCredentials.setEmail("admin");
		newCredentials.setPassword("admin");
		newCredentials.setRole(UserRole.admin);
		
		userCredentialsDao.saveNew(newCredentials);
		return newCredentials;
	}
}