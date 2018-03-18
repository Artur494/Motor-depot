package by.grsu.by.web.page.driver.panel;

import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

import by.grsu.by.dataaccess.impl.DriverDao;
import by.grsu.by.datamodel.Driver;
import by.grsu.by.web.page.driver.DriverEditPage;
import by.grsu.by.web.page.driver.DriversPage;

public class DriverListPanel extends Panel {

	private DriverDao driverDao;

	public DriverListPanel(String id, String rootFolderPath) {
		super(id);
		driverDao = new DriverDao(rootFolderPath);

		final List<Driver> driverList = driverDao.getAll();
		ListView<Driver> driverListView = new ListView<Driver>("rows", driverList) {
			@Override
			protected void populateItem(ListItem<Driver> item) {
				final Driver driver = item.getModelObject();
				item.add(new Label("id", driverList.indexOf(driver) + 1));
				item.add(new Label("name", driver.getName()));
				item.add(new Label("car", driver.toString()));

				item.add(new Link<Void>("edit-link") {
					@Override
					public void onClick() {
						setResponsePage(new DriverEditPage(driver, false));
					}
				});

				item.add(new Link<Void>("delete-link") {
					@Override
					public void onClick() {
						DriversPage page = new DriversPage();
						try {
							driverDao.delete(driver.getId());
						} catch (PersistenceException e) {
							page.error("The faculty can not be deleted");
						} finally {
							setResponsePage(new DriversPage());
						}
					}
				});
			}

		};
		add(driverListView);
	}

}
