package by.grsu.by.web.page.request;

import org.apache.wicket.markup.html.panel.FeedbackPanel;

import by.grsu.by.datamodel.Request;
import by.grsu.by.service.InvisibleLink;
import by.grsu.by.web.page.AbstractPage;
import by.grsu.by.web.page.request.panel.RequestListPanel;

public class RequestPage extends AbstractPage {
	public RequestPage() {
		add(new RequestListPanel("list-panel", "testXmlFolder"));

		add(new InvisibleLink("create") {
			@Override
			public void onClick() {
				setResponsePage(new RequestEditPage(new Request()));
			}
		});
		add(new FeedbackPanel("feedback"));

	}

}