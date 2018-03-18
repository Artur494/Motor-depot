package by.grsu.by.web.page.request.panel;

import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

import by.grsu.by.dataaccess.impl.RequestDao;
import by.grsu.by.datamodel.Request;
import by.grsu.by.web.page.request.RequestEditPage;
import by.grsu.by.web.page.request.RequestPage;

public class RequestListPanel extends Panel {

	private RequestDao requestDao;

	public RequestListPanel(String id, String rootFolderPath) {
		super(id);
		requestDao = new RequestDao(rootFolderPath);

		final List<Request> requestList = requestDao.getAll();
		ListView<Request> requestListView = new ListView<Request>("rows", requestList) {
			@Override
			protected void populateItem(ListItem<Request> item) {
				final Request request = item.getModelObject();
				item.add(new Label("id", requestList.indexOf(request)+1));
				item.add(new Label("status", request.getStatus()));
				item.add(new Label("condition", request.getCondition()));
				item.add(new Label("cruisingRange", request.getCruisingRange()));
				item.add(new Label("bodyType", request.getBodyType()));
				item.add(new Label("date", request.getDate()));

				item.add(new Link<Void>("edit-link") {
					@Override
					public void onClick() {
						setResponsePage(new RequestEditPage(request, false));
					}
				});

				item.add(new Link<Void>("delete-link") {
					@Override
					public void onClick() {
						RequestPage page = new RequestPage();
						try {
							requestDao.delete(request.getId());
						} catch (PersistenceException e) {
							page.error("The faculty can not be deleted");
						} finally {
							setResponsePage(page);
						}
					}
				});
			}
		};
		add(requestListView);
	}

}
