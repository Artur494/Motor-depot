package by.grsu.by.web.page.manager;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import by.grsu.by.web.page.AbstractPage;

public class ManagerPage extends AbstractPage{
	 
	public ManagerPage() {
	        super();
	       /* add(new CircleListPanel("list-panel","testXmlFolder"));

	        add(new Link("create") {
	            @Override
	            public void onClick() {
	                setResponsePage(new CircleEditPage(new Circle()));
	            }
	        });*/

	        add(new FeedbackPanel("feedback"));
	    }

	}