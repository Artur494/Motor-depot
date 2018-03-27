package by.grsu.by.web.page.car;

import org.apache.wicket.markup.html.panel.FeedbackPanel;

import by.grsu.by.datamodel.Car;
import by.grsu.by.service.InvisibleLink;
import by.grsu.by.web.page.AbstractPage;
import by.grsu.by.web.page.car.panel.CarListPanel;

public class CarsPage extends AbstractPage {
	public CarsPage() {
		super();
        add(new CarListPanel("list-panel","testXmlFolder"));

        add(new InvisibleLink("create") {
            @Override
            public void onClick() {
            	setResponsePage(new CarEditPage(new Car()));
            }
        });
		add(new FeedbackPanel("feedback"));
	}
	

}