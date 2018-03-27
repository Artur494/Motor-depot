package by.grsu.by.web.component.menu;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import by.grsu.by.service.InvisibleLink;
import by.grsu.by.service.InvisibleLinkDriver;
import by.grsu.by.service.InvisibleLinkForUsers;
import by.grsu.by.service.InvisibleLinkManager;
import by.grsu.by.web.app.AuthorizedSession;
import by.grsu.by.web.page.car.CarsPage;
import by.grsu.by.web.page.driver.DriversPage;
import by.grsu.by.web.page.flights.FlightsPage;
import by.grsu.by.web.page.home.HomePage;
import by.grsu.by.web.page.login.LoginPage;
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

		add(new InvisibleLinkManager("link-manajer") {
			@Override
			public void onClick() {
				setResponsePage(new ManagerPage());
			}
		});
		add(new InvisibleLinkForUsers("link-flights") {
			@Override
			public void onClick() {
				setResponsePage(new FlightsPage());
			}
		});
		add(new InvisibleLinkDriver("link-driver") {
			@Override
			public void onClick() {
				setResponsePage(new DriversPage());
			}
		});	
		add(new InvisibleLink("link-cars") {
			@Override
			public void onClick() {
				setResponsePage(new CarsPage());
			}
		});
		add(new InvisibleLink("link-request") {
			@Override
			public void onClick() {
				setResponsePage(new RequestPage());
			}
		});
		Link linkLogin = new Link("link-login") {
			@Override
			public void onClick() {
				setResponsePage(new LoginPage());
			}
		};
		
		linkLogin.setVisible(!AuthorizedSession.get().isSignedIn());
		add(linkLogin);
		
		Link link = new Link("link-logout") {
            @Override
            public void onClick() {
                getSession().invalidate();
                setResponsePage(LoginPage.class);
            }
        };
        link.setVisible(AuthorizedSession.get().isSignedIn());
        add(link);
		

	}

}