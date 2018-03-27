package by.grsu.by.web.page.manager;

import org.apache.wicket.authorization.Action;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeAction;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import by.grsu.by.web.page.AbstractPage;
import by.grsu.by.web.page.manager.panel.ManagerListPanel;

public class ManagerPage extends AbstractPage {

	public ManagerPage() {
		super();
		add(new ManagerListPanel("list-panel", "testXmlFolder"));
		add(new FeedbackPanel("feedback"));

	}

}