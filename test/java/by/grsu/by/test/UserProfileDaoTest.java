package by.grsu.by.test;

import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import by.grsu.by.dataaccess.impl.UserCredentialsDao;
import by.grsu.by.dataaccess.impl.UserProfileDao;
import by.grsu.by.datamodel.UserProfile;
import junit.framework.Assert;

public class UserProfileDaoTest {
	private static final String TEST_XML_FOLDER = "testXmlFolder";
	private static UserProfileDao userProfileDao;

	@BeforeClass
	public static void createDao() {
		userProfileDao = new UserProfileDao(TEST_XML_FOLDER);
	}

	@AfterClass
	public static void deleteTestXmlData() {
		// write code to clean up test results from FS

	}

	@Test
	public void testAdd() {
		System.out.println("Start 'save' test for user profile");
		final UserProfile newUserProfile = saveNewUserProfile();
		Assert.assertNotNull(userProfileDao.get(newUserProfile.getId()));
	}

//	@Test
//	public void testDelete() {
//		System.out.println("Start 'delete' test for profile");
//		final UserProfile newUserProfile = saveNewUserProfile();
//		userProfileDao.delete(newUserProfile.getId());
//		Assert.assertNull(userProfileDao.get(newUserProfile.getId()));
//	}

	@Test
	public void testGetAll() {
		System.out.println("Start 'getAll' test for profile");
		final int initialRowsCount = userProfileDao.getAll().size();
		saveNewUserProfile();
		Assert.assertEquals(userProfileDao.getAll().size(), initialRowsCount + 1);
	}

	private UserProfile saveNewUserProfile() {
		final UserProfile newUserProfile = new UserProfile();
		newUserProfile.setCreated(new Date());
		newUserProfile.setCredentials(new UserCredentialsDao(TEST_XML_FOLDER).get(2169223709592L));
		newUserProfile.setFirstName("user1");
		newUserProfile.setLastName("user1");
		
		userProfileDao.saveNew(newUserProfile);
		return newUserProfile;
	}
}