package by.grsu.by.web.page.manager.panel;

import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

import by.grsu.by.dataaccess.impl.CarDao;
import by.grsu.by.datamodel.Car;
import by.grsu.by.web.page.car.CarEditPage;
import by.grsu.by.web.page.car.CarsPage;

public class ManagerListPanel extends Panel {

	private CarDao carDao;

	public ManagerListPanel(String id, String rootFolderPath) {
		super(id);
		carDao = new CarDao(rootFolderPath);

		List<Car> carList = carDao.getAll();
		ListView<Car> carListView = new ListView<Car>("rows", carList) {
			@Override
			protected void populateItem(ListItem<Car> item) {
				final Car car = item.getModelObject();
				item.add(new Label("condition", car.getCondition()));
				item.add(new Label("characteristics", car.getCharacteristics()));

				item.add(new Link<Void>("edit-link") {
					@Override
					public void onClick() {
						setResponsePage(new CarEditPage(car));
					}
				});

				item.add(new Link<Void>("delete-link") {
					@Override
					public void onClick() {
						CarsPage page = new CarsPage();
						try {
							carDao.delete(car.getId());
						} catch (PersistenceException e) {
							page.error("The faculty can not be deleted");
						} finally {
							setResponsePage(page);
						}
					}
				});
			}
		};
		add(carListView);
	}

}
