package by.grsu.by.web.page.home;

import org.apache.wicket.markup.html.panel.FeedbackPanel;

import by.grsu.by.web.page.AbstractPage;

public class HomePage extends AbstractPage {
	public HomePage() {
		add(new FeedbackPanel("feedback"));

	}

}