package by.grsu.by.web.page.driver;

import org.apache.wicket.markup.html.panel.FeedbackPanel;

import by.grsu.by.datamodel.Driver;
import by.grsu.by.service.InvisibleLink;
import by.grsu.by.web.page.AbstractPage;
import by.grsu.by.web.page.driver.panel.DriverListPanel;

public class DriversPage extends AbstractPage {

	public DriversPage() {
		super();
		add(new DriverListPanel("list-panel", "testXmlFolder"));

		add(new InvisibleLink("create") {
			@Override
			public void onClick() {
				setResponsePage(new DriverEditPage(new Driver()));
			}
		});

		add(new FeedbackPanel("feedback"));
	}

}