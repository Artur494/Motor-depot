package by.grsu.by.web.page.request.panel;

import java.io.Serializable;
import java.util.Iterator;

import javax.persistence.PersistenceException;

import org.apache.wicket.authorization.Action;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeAction;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import by.grsu.by.datamodel.Request;
import by.grsu.by.datamodel.UserRole;
import by.grsu.by.service.InvisibleLabel;
import by.grsu.by.service.InvisibleLink;
import by.grsu.by.service.Service;
import by.grsu.by.service.impl.RequestServiceImpl;
import by.grsu.by.web.app.AuthorizedSession;
import by.grsu.by.web.page.request.RequestEditPage;
import by.grsu.by.web.page.request.RequestPage;

public class RequestListPanel extends Panel {

	private Service requestService;

	public RequestListPanel(String id, String rootFolderPath) {
		super(id);
		requestService = new RequestServiceImpl(rootFolderPath);

		RequestsDataProvider requestsDataProvider = new RequestsDataProvider();
		DataView<Request> requestDataView = new DataView<Request>("rows", requestsDataProvider)  {
			@Override
			protected void populateItem(Item<Request> item) {
				final Request request = item.getModelObject();
				item.add(new InvisibleLabel("id", request.getId()));
				item.add(new Label("status", request.getStatus()));
				item.add(new Label("cruisingRange", request.getCruisingRange()));
				item.add(new Label("bodyType", request.getBodyType()));
				item.add(new Label("date", request.getDate()));

				item.add(new InvisibleLink("edit-link") {
					@Override
					public void onClick() {
						setResponsePage(new RequestEditPage(request));
					}
				});

				item.add(new InvisibleLink("delete-link") {
					@Override
					public void onClick() {
						RequestPage page = new RequestPage();
						try {
							requestService.delete(request.getId());
						} catch (PersistenceException e) {
							page.error("The faculty can not be deleted");
						} finally {
							setResponsePage(page);
						}
					}
				});
			}
		};
		add(requestDataView);
		Label labelID = new Label("label-id", "ID");
		Label labelActions = new Label("label-actions", "Actions");
		labelID.setVisible(AuthorizedSession.get().getRoles().hasRole(UserRole.admin.toString()));
		labelActions.setVisible(AuthorizedSession.get().getRoles().hasRole(UserRole.admin.toString()));
		add(labelID);
		add(labelActions);
	}
	
	private class RequestsDataProvider extends SortableDataProvider<Request, Serializable> {

		@Override
		public Iterator<Request> iterator(long first, long count) {
			return requestService.getAll().iterator();
		}

		@Override
		public long size() {
			return requestService.getAll().size();
		}

		@Override
		public IModel<Request> model(Request object) {
			return new Model(object);
		}
	}

}
