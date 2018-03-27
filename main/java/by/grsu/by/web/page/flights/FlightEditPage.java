package by.grsu.by.web.page.flights;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.wicket.datetime.StyleDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import by.grsu.by.datamodel.Flight;
import by.grsu.by.datamodel.UserRole;
import by.grsu.by.service.Service;
import by.grsu.by.service.impl.FlightServiceImpl;
import by.grsu.by.web.app.AuthorizedSession;
import by.grsu.by.web.page.AbstractPage;

public class FlightEditPage extends AbstractPage {

	private Service flightService;
	private Flight flight;

	public FlightEditPage() {
		super();
		this.flight = new Flight();
		flightService = new FlightServiceImpl("testXmlFolder");
	}

	public FlightEditPage(Flight flight) {
		super();
		this.flight = flight;
		flightService = new FlightServiceImpl("testXmlFolder");
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		Form<Flight> form = new Form<Flight>("form", new CompoundPropertyModel<Flight>(flight));
		add(form);

		TextField<String> nameField = new TextField<String>("name", new PropertyModel<String>(this, "flight.name"));
		nameField.setLabel(new Model<String>("Flight Name"));
		nameField.setRequired(true);
		nameField.setEnabled(AuthorizedSession.get().getRoles().hasRole(UserRole.admin.toString()));
		form.add(nameField);

		final List<String> status = Arrays.asList(new String[] { "In processing", "Ñanceled", "Arrived" });
		final DropDownChoice<String> dropStatus = new DropDownChoice<String>("status", status);
		dropStatus.setLabel(new Model<String>("Status"));
		dropStatus.setRequired(true);
		form.add(dropStatus);

		DateTextField dateTextField = new DateTextField("dateTextField", new PropertyModel<Date>(this, "flight.date"),
				new StyleDateConverter("S-", true));
		dateTextField.setLabel(new Model<String>("Date"));
		dateTextField.setRequired(true);
		dateTextField.setEnabled(AuthorizedSession.get().getRoles().hasRole(UserRole.admin.toString()));
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
				flightService.saveOrUpdate(flight);
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

	private class FlightForm<T> extends Form<T> {

		public FlightForm(String id, IModel<T> model) {
			super(id, model);
		}
	}

}
