package by.grsu.by.web.page.flight.panel;

import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

import by.grsu.by.dataaccess.impl.FlightDao;
import by.grsu.by.datamodel.Flight;
import by.grsu.by.web.page.car.CarsPage;
import by.grsu.by.web.page.flights.FlightEditPage;
import by.grsu.by.web.page.flights.FlightsPage;

public class FlightListPanel extends Panel {

	private FlightDao flightDao;

	public FlightListPanel(String id, String rootFolderPath) {
		super(id);
		flightDao = new FlightDao(rootFolderPath);

		final List<Flight> flightList = flightDao.getAll();
		ListView<Flight> flightListView = new ListView<Flight>("rows", flightList) {
			@Override
			protected void populateItem(ListItem<Flight> item) {
				final Flight flight = item.getModelObject();
				item.add(new Label("id", flightList.indexOf(flight)+1));
				item.add(new Label("status", flight.getStatus()));
				item.add(new Label("date", flight.getDate()));

				item.add(new Link<Void>("edit-link") {
					@Override
					public void onClick() {
						setResponsePage(new FlightEditPage(flight, false));
					}
				});

				item.add(new Link<Void>("delete-link") {
					@Override
					public void onClick() {
						FlightsPage page = new FlightsPage();
						try {
							flightDao.delete(flight.getId());
						} catch (PersistenceException e) {
							page.error("The faculty can not be deleted");
						} finally {
							setResponsePage(new FlightsPage());
						}
					}
				});
			}
		};
		add(flightListView);
	}

}
