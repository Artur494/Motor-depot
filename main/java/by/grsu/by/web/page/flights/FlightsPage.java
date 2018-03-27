package by.grsu.by.web.page.flights;

import org.apache.wicket.markup.html.panel.FeedbackPanel;

import by.grsu.by.web.page.AbstractPage;
import by.grsu.by.web.page.flight.panel.FlightListPanel;

public class FlightsPage extends AbstractPage {
	public FlightsPage() {
		super();
		add(new FlightListPanel("list-panel", "testXmlFolder"));
		add(new FeedbackPanel("feedback"));

	}

}