package by.grsu.by.web.page.car;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import by.grsu.by.datamodel.Car;
import by.grsu.by.datamodel.Driver;
import by.grsu.by.web.page.AbstractPage;
import by.grsu.by.web.page.car.panel.CarListPanel;
import by.grsu.by.web.page.driver.DriverEditPage;

public class CarsPage extends AbstractPage {
	public CarsPage() {
		super();
        add(new CarListPanel("list-panel","testXmlFolder"));

        add(new Link("create") {
            @Override
            public void onClick() {
            	setResponsePage(new CarEditPage(new Car(), true));
            }
        });
		add(new FeedbackPanel("feedback"));

	}

}