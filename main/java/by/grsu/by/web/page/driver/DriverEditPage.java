package by.grsu.by.web.page.driver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import by.grsu.by.datamodel.Car;
import by.grsu.by.datamodel.Driver;
import by.grsu.by.service.Service;
import by.grsu.by.service.impl.CarServiceImpl;
import by.grsu.by.service.impl.DriverServiceImpl;
import by.grsu.by.web.page.AbstractPage;

public class DriverEditPage extends AbstractPage {

	private Service carService, driverService;
	private Driver driver;

	public DriverEditPage() {
		super();
		this.driver = new Driver();
		driverService = new DriverServiceImpl("testXmlFolder");
	}

	public DriverEditPage(Driver driver) {
		super();
		this.driver = driver;
		driverService = new DriverServiceImpl("testXmlFolder");
		carService = new CarServiceImpl("testXmlFolder");
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		Form<Driver> form = new Form<Driver>("form", new CompoundPropertyModel<Driver>(driver));
		add(form);

		TextField<String> nameField = new TextField<String>("name");
		nameField.setLabel(new Model<String>("Name"));
		nameField.setRequired(true);
		form.add(nameField);

		final Set<Car> freeCars = new HashSet<Car>(carService.getAll());
		final HashSet<Long> freeCarsIds = new HashSet<Long>();
		
		for (Car car : freeCars) {
			freeCarsIds.add(car.getId().longValue());
			
		}
		
		for (Driver dr : (List<Driver>)driverService.getAll()) {
			if (freeCarsIds.contains(dr.getCar().getId().longValue())) {
				freeCarsIds.remove(dr.getCar().getId().longValue());
			}
		}
		freeCars.clear();
		for (Car car : (List<Car>)carService.getAll()) {
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
				driverService.saveOrUpdate(driver);
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
	
	private class DriverForm<T> extends Form<T> {

		public DriverForm(String id, IModel<T> model) {
			super(id, model);
		}
	}

}
