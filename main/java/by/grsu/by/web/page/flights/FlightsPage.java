package by.grsu.by.web.page.flights;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import by.grsu.by.datamodel.Flight;
import by.grsu.by.web.page.AbstractPage;
import by.grsu.by.web.page.flight.panel.FlightListPanel;

public class FlightsPage extends AbstractPage {
	public FlightsPage() {
		add(new FlightListPanel("list-panel","testXmlFolder"));

        add(new Link("create") {
            @Override
            public void onClick() {
                setResponsePage(new FlightEditPage(new Flight(), true));
            }
        });
		add(new FeedbackPanel("feedback"));

	}

}