package by.grsu.by.test;

import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import by.grsu.by.dataaccess.impl.FlightDao;
import by.grsu.by.datamodel.Flight;
import junit.framework.Assert;

public class FlightDaoTest {

	private static final String TEST_XML_FOLDER = "testXmlFolder";
	private static FlightDao flightDao;

	@BeforeClass
	public static void createDao() {
		flightDao = new FlightDao(TEST_XML_FOLDER);
	}

		
	@AfterClass
	public static void deleteTestXmlData() {
	//	 write code to clean up test results from FS
		
	}

	@Test
	public void testAdd() {
		System.out.println("Start 'save' test for Flight");
		final Flight newFlight = saveNewFlight();
		Assert.assertNotNull(flightDao.get(newFlight.getId()));
	}

	 @Test
	 public void testDelete() {
	 System.out.println("Start 'delete' test for Flight");
	 final Flight newFlight = saveNewFlight();
	 flightDao.delete(newFlight.getId());
	 Assert.assertNull(flightDao.get(newFlight.getId()));
	 }
	
	 @Test
	 public void testGetAll() {
	 System.out.println("Start 'getAll' test for Flight");
	 final int initialRowsCount = flightDao.getAll().size();
	 saveNewFlight();
	 Assert.assertEquals(flightDao.getAll().size(), initialRowsCount + 1);
	 }

	private Flight saveNewFlight() {
		final Flight newFlight = new Flight();
		
		GregorianCalendar calendar = new GregorianCalendar(1999, 03, 15);
		Date date = calendar.getTime();
		
		newFlight.setDate(date);
		newFlight.setStatus("good");
		flightDao.saveNew(newFlight);
		return newFlight;
	}
}