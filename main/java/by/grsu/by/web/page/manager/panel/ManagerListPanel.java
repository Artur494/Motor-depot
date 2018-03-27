package by.grsu.by.web.page.manager.panel;

import java.io.Serializable;
import java.util.Iterator;

import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import by.grsu.by.datamodel.Request;
import by.grsu.by.datamodel.UserRole;
import by.grsu.by.service.InvisibleLabel;
import by.grsu.by.service.InvisibleLinkManager;
import by.grsu.by.service.Service;
import by.grsu.by.service.impl.RequestServiceImpl;
import by.grsu.by.web.app.AuthorizedSession;
import by.grsu.by.web.page.manager.ManagerConsiderPage;

public class ManagerListPanel extends Panel {

	private Service requestService;

	public ManagerListPanel(String id, String rootFolderPath) {
		super(id);
		requestService = new RequestServiceImpl(rootFolderPath);

		RequestsDataProvider requestsDataProvider = new RequestsDataProvider();
		DataView<Request> requestDataView = new DataView<Request>("rows", requestsDataProvider) {
			@Override
			protected void populateItem(Item<Request> item) {
				final Request flightRequest = item.getModelObject();
				item.add(new InvisibleLabel("id", flightRequest.getId()));
				item.add(new Label("status", flightRequest.getStatus()));
				item.add(new Label("cruisingRange", flightRequest.getCruisingRange()));
				item.add(new Label("bodyType", flightRequest.getBodyType()));
				item.add(new Label("date", flightRequest.getDate()));

				item.add(new InvisibleLinkManager("consider") {
					@Override
					public void onClick() {
						setResponsePage(new ManagerConsiderPage(flightRequest));
					}
				});

			}
		};
		add(requestDataView);
		Label labelID = new Label("label-id", "ID");
		Label labelActions = new Label("label-actions", "Actions");
		labelID.setVisible(AuthorizedSession.get().getRoles().hasRole(UserRole.admin.toString()));
		if (AuthorizedSession.get().getRoles().hasRole(UserRole.admin.toString())
				|| AuthorizedSession.get().getRoles().hasRole(UserRole.manager.toString())) {
			labelActions.setVisible(true);
		} else {
			labelActions.setVisible(false);
		}
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
