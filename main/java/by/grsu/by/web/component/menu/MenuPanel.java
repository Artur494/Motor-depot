package by.grsu.by.web.component.menu;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import by.grsu.by.web.page.car.CarsPage;
import by.grsu.by.web.page.driver.DriversPage;
import by.grsu.by.web.page.flights.FlightsPage;
import by.grsu.by.web.page.home.HomePage;
import by.grsu.by.web.page.manager.ManagerPage;
import by.grsu.by.web.page.request.RequestPage;

public class MenuPanel extends Panel {

	public MenuPanel(String id) {
		super(id);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		add(new Link("link-home") {
			@Override
			public void onClick() {
				setResponsePage(new HomePage());
			}
		});

		add(new Link("link-manajer") {
			@Override
			public void onClick() {
				setResponsePage(new ManagerPage());
			}
		});
		add(new Link("link-flights") {
			@Override
			public void onClick() {
				setResponsePage(new FlightsPage());
			}
		});
		add(new Link("link-driver") {
			@Override
			public void onClick() {
				setResponsePage(new DriversPage());
			}
		});
		add(new Link("link-cars") {
			@Override
			public void onClick() {
				setResponsePage(new CarsPage());
			}
		});
		add(new Link("link-request") {
			@Override
			public void onClick() {
				setResponsePage(new RequestPage());
			}
		});
		

	}
}