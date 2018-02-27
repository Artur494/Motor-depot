package by.grsu.by.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import by.grsu.by.dataaccess.impl.ManagerDao;
import by.grsu.by.datamodel.Manager;
import junit.framework.Assert;

public class ManagerDaoTest {

	private static final String TEST_XML_FOLDER = "testXmlFolder";
	private static ManagerDao managerDao;

	@BeforeClass
	public static void createDao() {
		managerDao = new ManagerDao(TEST_XML_FOLDER);
	}

		
	@AfterClass
	public static void deleteTestXmlData() {
	//	 write code to clean up test results from FS
		
	}

	@Test
	public void testAdd() {
		System.out.println("Start 'save' test for Manager");
		final Manager newManager = saveNewManager();
		Assert.assertNotNull(managerDao.get(newManager.getId()));
	}

	 @Test
	 public void testDelete() {
	 System.out.println("Start 'delete' test for Manager");
	 final Manager newManager = saveNewManager();
	 managerDao.delete(newManager.getId());
	 Assert.assertNull(managerDao.get(newManager.getId()));
	 }
	
	 @Test
	 public void testGetAll() {
	 System.out.println("Start 'getAll' test for Manager");
	 final int initialRowsCount = managerDao.getAll().size();
	 saveNewManager();
	 Assert.assertEquals(managerDao.getAll().size(), initialRowsCount + 1);
	 }

	private Manager saveNewManager() {
		final Manager newManager = new Manager();
		newManager.setFirstName("Alex");
		newManager.setLastName("Black");
		managerDao.saveNew(newManager);
		return newManager;
	}
}