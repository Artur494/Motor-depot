package by.grsu.by.web.page.driver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;

import by.grsu.by.dataaccess.impl.CarDao;
import by.grsu.by.dataaccess.impl.DriverDao;
import by.grsu.by.datamodel.Car;
import by.grsu.by.datamodel.Characteristics;
import by.grsu.by.datamodel.Driver;

public class DriverEditPage extends WebPage {

	private DriverDao driverDao;
	private CarDao carDao;
	private Boolean selectCreateOrUpdate = true;
	private Driver driver;

	public DriverEditPage(Driver driver) {
		super();
		this.driver = driver;
		driverDao = new DriverDao("testXmlFolder");
	}

	public DriverEditPage(Driver driver, Boolean selectCreateOrUpdate) {
		super();
		this.driver = driver;
		this.selectCreateOrUpdate = selectCreateOrUpdate;
		driverDao = new DriverDao("testXmlFolder");
		carDao = new CarDao("testXmlFolder");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.wicket.Page#onInitialize()
	 */
	@Override
	protected void onInitialize() {
		super.onInitialize();

		Form<Driver> form = new Form<Driver>("form", new CompoundPropertyModel<Driver>(driver));
		add(form);

		TextField<String> nameField = new TextField<String>("name");
		nameField.setLabel(new Model<String>("Name"));
		nameField.setRequired(true);
		form.add(nameField);

		final Set<Car> freeCars = new HashSet<Car>(carDao.getAll());
		final HashSet<Long> freeCarsIds = new HashSet<Long>();
		
		for (Car car : freeCars) {
			freeCarsIds.add(car.getId().longValue());
			
		}
		
		for (Driver dr : driverDao.getAll()) {
			if (freeCarsIds.contains(dr.getCar().getId().longValue())) {
				freeCarsIds.remove(dr.getCar().getId().longValue());
			}
		}
		freeCars.clear();
		for (Car car : carDao.getAll()) {
			if (freeCarsIds.contains(car.getId().longValue())) {
				freeCars.add(car);
			}
		}

		final DropDownChoice<Car> dropCar = new DropDownChoice<Car>("car",new ArrayList(freeCars));
		dropCar.setLabel(new Model<String>("Car"));
		dropCar.setRequired(true);
		form.add(dropCar);

		form.add(new SubmitLink("save") {
			@Override
			public void onSubmit() {
				super.onSubmit();
				if (selectCreateOrUpdate == true) {
					driverDao.saveNew(driver);
				} else {
					driverDao.update(driver);
				}
				setResponsePage(new DriversPage());
			}
		});

		form.add(new Link("cancel") {

			@Override
			public void onClick() {
				setResponsePage(new DriversPage());
			}
		});
		add(new FeedbackPanel("feedback"));
	}

}
