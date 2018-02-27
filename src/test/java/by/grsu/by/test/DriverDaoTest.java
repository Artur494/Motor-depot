package by.grsu.by.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import by.grsu.by.dataaccess.impl.DriverDao;
import by.grsu.by.datamodel.Car;
import by.grsu.by.datamodel.Driver;
import by.grsu.by.datamodel.Flight;
import junit.framework.Assert;

public class DriverDaoTest {

	private static final String TEST_XML_FOLDER = "testXmlFolder";
	private static DriverDao driverDao;

	@BeforeClass
	public static void createDao() {
		driverDao = new DriverDao(TEST_XML_FOLDER);
	}

		
	@AfterClass
	public static void deleteTestXmlData() {
	//	 write code to clean up test results from FS
		
	}

	@Test
	public void testAdd() {
		System.out.println("Start 'save' test for Driver");
		final Driver newDriver = saveNewDriver();
		Assert.assertNotNull(driverDao.get(newDriver.getId()));
	}

	 @Test
	 public void testDelete() {
	 System.out.println("Start 'delete' test for Driver");
	 final Driver newDriver = saveNewDriver();
	 driverDao.delete(newDriver.getId());
	 Assert.assertNull(driverDao.get(newDriver.getId()));
	 }
	
	 @Test
	 public void testGetAll() {
	 System.out.println("Start 'getAll' test for Driver");
	 final int initialRowsCount = driverDao.getAll().size();
	 saveNewDriver();
	 Assert.assertEquals(driverDao.getAll().size(), initialRowsCount + 1);
	 }

	private Driver saveNewDriver() {
		final Driver newDriver = new Driver();
		
		final Car car = new Car();
		car.setCondition("normal");
		car.setCharacteristics(true);
		
		List<Flight> flights = new ArrayList<Flight>();
		Flight f = new Flight();
		GregorianCalendar calendar = new GregorianCalendar(1999, 03, 15);
		Date date = calendar.getTime();
		f.setDate(date);
		f.setStatus("good");
		flights.add(f);
		
		newDriver.setCar(car);
		newDriver.setFlights(flights);
		driverDao.saveNew(newDriver);
		return newDriver;
	}
}