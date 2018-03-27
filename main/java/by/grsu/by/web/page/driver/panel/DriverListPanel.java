package by.grsu.by.web.page.driver.panel;

import java.io.Serializable;
import java.util.Iterator;

import javax.persistence.PersistenceException;

import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import by.grsu.by.datamodel.Driver;
import by.grsu.by.datamodel.UserRole;
import by.grsu.by.service.InvisibleLabel;
import by.grsu.by.service.InvisibleLink;
import by.grsu.by.service.Service;
import by.grsu.by.service.impl.DriverServiceImpl;
import by.grsu.by.web.app.AuthorizedSession;
import by.grsu.by.web.page.driver.DriverEditPage;
import by.grsu.by.web.page.driver.DriversPage;

public class DriverListPanel extends Panel {

	private Service driverService;

	public DriverListPanel(String id, String rootFolderPath) {
		super(id);
		driverService = new DriverServiceImpl(rootFolderPath);

		DriversDataProvider driversDataProvider = new DriversDataProvider();
		DataView<Driver> driverDataView = new DataView<Driver>("rows", driversDataProvider)  {
			@Override
			protected void populateItem(Item<Driver> item) {
				final Driver driver = item.getModelObject();
				item.add(new InvisibleLabel("id", driver.getId()));
				item.add(new Label("name", driver.getName()));
				item.add(new Label("carModel", driver.getCar().getCarModel()));
				item.add(new Label("condition", driver.getCar().getCondition()));
				item.add(new Label("cruisingRange", driver.getCar().getCruisingRange()));
				item.add(new Label("bodyType", driver.getCar().getBodyType()));
				item.add(new Label("numberCar", driver.getCar().getNumberCar()));

				item.add(new InvisibleLink("edit-link") {
					@Override
					public void onClick() {
						setResponsePage(new DriverEditPage(driver));
					}
				});

				item.add(new InvisibleLink("delete-link") {
					@Override
					public void onClick() {
						DriversPage page = new DriversPage();
						try {
							driverService.delete(driver.getId());
						} catch (PersistenceException e) {
							page.error("The faculty can not be deleted");
						} finally {
							setResponsePage(page);
						}
					}
				});
			}

		};
		add(driverDataView);
		Label labelID = new Label("label-id", "ID");
		Label labelActions = new Label("label-actions", "Actions");
		labelID.setVisible(AuthorizedSession.get().getRoles().hasRole(UserRole.admin.toString()));
		labelActions.setVisible(AuthorizedSession.get().getRoles().hasRole(UserRole.admin.toString()));
		add(labelID);
		add(labelActions);
	}
	
	private class DriversDataProvider extends SortableDataProvider<Driver, Serializable> {

		@Override
		public Iterator<Driver> iterator(long first, long count) {
			return driverService.getAll().iterator();
		}

		@Override
		public long size() {
			return driverService.getAll().size();
		}

		@Override
		public IModel<Driver> model(Driver object) {
			return new Model(object);
		}
	}
}
