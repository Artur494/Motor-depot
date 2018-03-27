package by.grsu.by.web.page.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.datetime.StyleDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import by.grsu.by.datamodel.Driver;
import by.grsu.by.datamodel.Flight;
import by.grsu.by.datamodel.Request;
import by.grsu.by.service.Service;
import by.grsu.by.service.impl.DriverServiceImpl;
import by.grsu.by.service.impl.FlightServiceImpl;
import by.grsu.by.service.impl.RequestServiceImpl;
import by.grsu.by.web.page.AbstractPage;
import by.grsu.by.web.page.flights.FlightsPage;

public class ManagerConsiderPage extends AbstractPage {

	private Service requestService, driverService, flightService;
	private Flight flight;
	public Request flightRequest;
	private Driver selectedDriver;

	public ManagerConsiderPage() {
		super();
		this.flightRequest = new Request();
		driverService = new DriverServiceImpl("testXmlFolder");
		requestService = new RequestServiceImpl("testXmlFolder");
		flightService = new FlightServiceImpl("testXmlFolder");
	}

	public ManagerConsiderPage(Request request) {
		super();
		this.flightRequest = request;
		driverService = new DriverServiceImpl("testXmlFolder");
		requestService = new RequestServiceImpl("testXmlFolder");
		flightService = new FlightServiceImpl("testXmlFolder");
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		Form<Request> form = new Form<Request>("form", new CompoundPropertyModel<Request>(flightRequest));
		add(form);

		final List<Request> requestList = Arrays.asList(flightRequest);
		ListView<Request> requestListView = new ListView<Request>("rows", requestList) {
			@Override
			protected void populateItem(ListItem<Request> item) {
				final Request request = item.getModelObject();
				item.add(new Label("status", request.getStatus()));
				item.add(new Label("cruisingRange", request.getCruisingRange()));
				item.add(new Label("bodyType", request.getBodyType()));
				item.add(new Label("date", request.getDate()));

			}
		};
		add(requestListView);

		final List<Driver> listRelevantDrivers = new ArrayList<Driver>();

		for (Driver dr : (List<Driver>) driverService.getAll()) {
			if ((dr.getCar().getBodyType().hashCode() == flightRequest.getBodyType().hashCode())
					&& (dr.getCar().getCruisingRange().hashCode() == flightRequest.getCruisingRange().hashCode())
					&& (dr.getCar().getCondition().equals("Ready for flight"))) {
				listRelevantDrivers.add(dr);
			}
		}

		final RadioGroup group = new RadioGroup("group");
		group.setLabel(new Model<String>("select driver"));
		group.setRequired(true);

		add(group);

		final ListView<Driver> driverListView = new ListView<Driver>("rowsDrivers", listRelevantDrivers) {
			@Override
			protected void populateItem(ListItem<Driver> item) {

				final Driver dr = item.getModelObject();
				selectedDriver = dr;
				item.add(new Label("id", listRelevantDrivers.indexOf(dr) + 1));
				item.add(new Label("name", dr.getName()));
				item.add(new Label("carModel", dr.getCar().getCarModel()));
				item.add(new Label("condition", dr.getCar().getCondition()));
				item.add(new Label("cruisingRange", dr.getCar().getCruisingRange()));
				item.add(new Label("bodyType", dr.getCar().getBodyType()));
				Radio radio = new Radio("radio", item.getModel());

				AjaxEventBehavior clickAction = new AjaxEventBehavior("click") {
					protected void onEvent(AjaxRequestTarget target) {
						selectedDriver = dr;
					}
				};
				radio.add(clickAction);
				item.add(radio);
			}
		};

		Label label = new Label("label-id", "ID");
		Label lListDriversOfEmpty = new Label("label-DriverOfEmpty", "List of Drivers is Empty");
		lListDriversOfEmpty.setVisible(false);

		if (listRelevantDrivers.isEmpty()) {
			driverListView.setVisible(false);
			label.setVisible(false);
			lListDriversOfEmpty.setVisible(true);
			flightRequest.setStatus("Refused");
		}

		add(label);
		add(lListDriversOfEmpty);
		add(driverListView);
		group.add(driverListView);

		TextField<String> nameField = new TextField<String>("name", new PropertyModel<String>(this, "flight.name"));
		nameField.setLabel(new Model<String>("Flight Name"));
		nameField.setRequired(true);
		form.add(nameField);

		DateTextField dateTextField = new DateTextField("dateTextField", new PropertyModel<Date>(this, "flight.date"),
				new StyleDateConverter("S-", true));
		dateTextField.setLabel(new Model<String>("Date"));
		dateTextField.setRequired(true);
		form.add(dateTextField);

		DatePicker datePicker = new DatePicker() {

			@Override
			protected String getAdditionalJavaScript() {
				return "${calendar}.cfg.setProperty(\"navigator\",true,false); ${calendar}.render();";
			}
		};

		datePicker.setShowOnFieldClick(true);
		datePicker.setAutoHide(true);
		dateTextField.add(datePicker);

		final Label labelCreate = new Label("message", "List of Drivers is Empty");
		labelCreate.setVisible(false);
		form.add(new SubmitLink("createFlight") {
			@Override
			public void onSubmit() {
				super.onSubmit();
				
				if (listRelevantDrivers.isEmpty()) {
					labelCreate.setVisible(true);
				} else {
					flight.setStatus("In processing");
					flight.setDriver(selectedDriver);
					flightService.saveNew(flight);
					flightRequest.setStatus("Approved");
					requestService.saveOrUpdate(flightRequest);
					setResponsePage(new FlightsPage());
				}
			}
		});
		form.add(labelCreate);
		
		form.add(new Link<Object>("cancel") {

			@Override
			public void onClick() {
				setResponsePage(new ManagerPage());
			}
		});
		add(new FeedbackPanel("feedback"));
	}

	private class ManagerForm<T> extends Form<T> {

		public ManagerForm(String id, IModel<T> model) {
			super(id, model);
		}
	}

}
