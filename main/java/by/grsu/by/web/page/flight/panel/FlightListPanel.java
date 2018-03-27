package by.grsu.by.web.page.flight.panel;

import java.io.Serializable;
import java.util.Iterator;

import javax.persistence.PersistenceException;

import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import by.grsu.by.datamodel.Flight;
import by.grsu.by.datamodel.UserRole;
import by.grsu.by.service.InvisibleLabel;
import by.grsu.by.service.InvisibleLink;
import by.grsu.by.service.InvisibleLinkDriver;
import by.grsu.by.service.Service;
import by.grsu.by.service.impl.FlightServiceImpl;
import by.grsu.by.web.app.AuthorizedSession;
import by.grsu.by.web.page.flights.FlightEditPage;
import by.grsu.by.web.page.flights.FlightsPage;

public class FlightListPanel extends Panel {

	private Service flightService;

	public FlightListPanel(String id, String rootFolderPath) {
		super(id);
		flightService = new FlightServiceImpl(rootFolderPath);

		FlightsDataProvider flightsDataProvider = new FlightsDataProvider();
		DataView<Flight> flightDataView = new DataView<Flight>("rows", flightsDataProvider) {
			@Override
			protected void populateItem(Item<Flight> item) {
				final Flight flight = item.getModelObject();
				item.add(new InvisibleLabel("id", flight.getId()));
				item.add(new Label("name", flight.getName()));
				item.add(new Label("driverName", flight.getDriver().getName()));
				item.add(new Label("carModel", flight.getDriver().getCar().getCarModel()));
				item.add(new Label("condition", flight.getDriver().getCar().getCondition()));
				item.add(new Label("cruisingRange", flight.getDriver().getCar().getCruisingRange()));
				item.add(new Label("bodyType", flight.getDriver().getCar().getBodyType()));
				item.add(new Label("status", flight.getStatus()));
				item.add(new Label("date", flight.getDate()));

				item.add(new InvisibleLinkDriver("edit-link") {
					@Override
					public void onClick() {
						setResponsePage(new FlightEditPage(flight));
					}
				});

				item.add(new InvisibleLink("delete-link") {
					@Override
					public void onClick() {
						FlightsPage page = new FlightsPage();
						try {
							flightService.delete(flight.getId());
						} catch (PersistenceException e) {
							page.error("The faculty can not be deleted");
						} finally {
							setResponsePage(new FlightsPage());
						}
					}
				});
			}
		};
		add(flightDataView);
		Label labelID = new Label("label-id", "ID");
		Label labelActions = new Label("label-actions", "Actions");
		labelID.setVisible(AuthorizedSession.get().getRoles().hasRole(UserRole.admin.toString()));
		if (AuthorizedSession.get().getRoles().hasRole(UserRole.admin.toString()) || AuthorizedSession.get().getRoles().hasRole(UserRole.driver.toString())){
			labelActions.setVisible(true);
		} else {
			labelActions.setVisible(false);
		}
		add(labelID);
		add(labelActions);
	}
	private class FlightsDataProvider extends SortableDataProvider<Flight, Serializable> {

		@Override
		public Iterator<Flight> iterator(long first, long count) {
			return flightService.getAll().iterator();
		}

		@Override
		public long size() {
			return flightService.getAll().size();
		}

		@Override
		public IModel<Flight> model(Flight object) {
			return new Model(object);
		}
	}

}
