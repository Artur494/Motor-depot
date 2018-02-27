package by.grsu.by.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import by.grsu.by.dataaccess.impl.RequestDao;
import by.grsu.by.datamodel.Request;
import junit.framework.Assert;

public class RequestDaoTest {

	private static final String TEST_XML_FOLDER = "testXmlFolder";
	private static RequestDao requestDao;

	@BeforeClass
	public static void createDao() {
		requestDao = new RequestDao(TEST_XML_FOLDER);
	}

		
	@AfterClass
	public static void deleteTestXmlData() {
	//	 write code to clean up test results from FS
		
	}

	@Test
	public void testAdd() {
		System.out.println("Start 'save' test for Request");
		final Request newRequest = saveNewRequest();
		Assert.assertNotNull(requestDao.get(newRequest.getId()));
	}

	 @Test
	 public void testDelete() {
	 System.out.println("Start 'delete' test for Request");
	 final Request newRequest = saveNewRequest();
	 requestDao.delete(newRequest.getId());
	 Assert.assertNull(requestDao.get(newRequest.getId()));
	 }
	
	 @Test
	 public void testGetAll() {
	 System.out.println("Start 'getAll' test for Request");
	 final int initialRowsCount = requestDao.getAll().size();
	 saveNewRequest();
	 Assert.assertEquals(requestDao.getAll().size(), initialRowsCount + 1);
	 }

	private Request saveNewRequest() {
		final Request newRequest = new Request();
		newRequest.setConditionCar("normal");
		newRequest.setPerformanceCar(true);;
		requestDao.saveNew(newRequest);
		return newRequest;
	}
}