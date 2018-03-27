package by.grsu.by.web.app;

import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;

import by.grsu.by.web.page.home.HomePage;
import by.grsu.by.web.page.login.LoginPage;

public class WicketApplication extends AuthenticatedWebApplication {

	@Override
	public void init() {
		super.init();
		//mountPage("/home", HomePage.class);
		//mountPage("/edit", CircleEditPage.class);
	}

	@Override
	protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
		return AuthorizedSession.class;
	}

	@Override
	protected Class<? extends WebPage> getSignInPageClass() {
		return LoginPage.class;
	}

	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage() {
		return HomePage.class;
	}

}
