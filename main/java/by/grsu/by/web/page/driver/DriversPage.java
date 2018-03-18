package by.grsu.by.web.page.driver;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import by.grsu.by.datamodel.Driver;
import by.grsu.by.web.page.AbstractPage;
import by.grsu.by.web.page.driver.panel.DriverListPanel;

public class DriversPage extends AbstractPage {

	public DriversPage() {
		super();
		add(new DriverListPanel("list-panel", "testXmlFolder"));

		add(new Link("create") {
			@Override
			public void onClick() {
				setResponsePage(new DriverEditPage(new Driver(), true));
			}
		});

		add(new FeedbackPanel("feedback"));
	}

}