package by.grsu.by.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import by.grsu.by.dataaccess.impl.CarDao;
import by.grsu.by.datamodel.Car;
import junit.framework.Assert;

public class CarDaoTest {

	private static final String TEST_XML_FOLDER = "testXmlFolder";
	private static CarDao carDao;

	@BeforeClass
	public static void createDao() {
		carDao = new CarDao(TEST_XML_FOLDER);
	}

		
	@AfterClass
	public static void deleteTestXmlData() {
	//	 write code to clean up test results from FS
		
	}

	@Test
	public void testAdd() {
		System.out.println("Start 'save' test for Car");
		final Car newCar = saveNewCar();
		Assert.assertNotNull(carDao.get(newCar.getId()));
	}

	 @Test
	 public void testDelete() {
	 System.out.println("Start 'delete' test for Car");
	 final Car newCar = saveNewCar();
	 carDao.delete(newCar.getId());
	 Assert.assertNull(carDao.get(newCar.getId()));
	 }
	
	 @Test
	 public void testGetAll() {
	 System.out.println("Start 'getAll' test for Car");
	 final int initialRowsCount = carDao.getAll().size();
	 saveNewCar();
	 Assert.assertEquals(carDao.getAll().size(), initialRowsCount + 1);
	 }

	private Car saveNewCar() {
		final Car newCar = new Car();
		newCar.setCondition("normal");
		newCar.setCharacteristics(true);
		carDao.saveNew(newCar);
		return newCar;
	}
}