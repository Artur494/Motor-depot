package by.grsu.by.web.page.flights;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.wicket.datetime.StyleDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import by.grsu.by.dataaccess.impl.FlightDao;
import by.grsu.by.datamodel.Flight;

public class FlightEditPage extends WebPage {

	private FlightDao flightDao;
	private Boolean selectCreateOrUpdate = true;
	private Flight flight;

	public FlightEditPage(Flight flight, Boolean selectCreateOrUpdate) {
		super();
		this.flight = flight;
		flightDao = new FlightDao("testXmlFolder");
		this.selectCreateOrUpdate = selectCreateOrUpdate;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		Form<Flight> form = new Form<Flight>("form", new CompoundPropertyModel<Flight>(flight));
		add(form);

		final List<String> status = Arrays.asList(new String[] {"Cancelled ", "Scheduled", "Boarding", "Delayed", "Arrived" });
		final DropDownChoice<String> dropStatus = new DropDownChoice<String>("status", status);
		dropStatus.setLabel(new Model<String>("Status"));
		dropStatus.setRequired(true);
		form.add(dropStatus);
		

		DateTextField dateTextField = new DateTextField("dateTextField", new PropertyModel<Date>(this, "flight.date"),
				new StyleDateConverter("S-", true));
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

		form.add(new SubmitLink("save") {
			@Override
			public void onSubmit() {
				super.onSubmit();
				if (selectCreateOrUpdate == true) {
					flightDao.saveNew(flight);
				} else {
					flightDao.update(flight);
				}
				setResponsePage(new FlightsPage());
			}
		});

		form.add(new Link("cancel") {

			@Override
			public void onClick() {
				setResponsePage(new FlightsPage());
			}
		});
		add(new FeedbackPanel("feedback"));
	}


}
