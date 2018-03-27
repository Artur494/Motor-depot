package by.grsu.by.web.page.car.panel;

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

import by.grsu.by.datamodel.Car;
import by.grsu.by.datamodel.UserRole;
import by.grsu.by.service.InvisibleLabel;
import by.grsu.by.service.InvisibleLink;
import by.grsu.by.service.Service;
import by.grsu.by.service.impl.CarServiceImpl;
import by.grsu.by.web.app.AuthorizedSession;
import by.grsu.by.web.page.car.CarEditPage;
import by.grsu.by.web.page.car.CarsPage;

public class CarListPanel extends Panel {

	private Service carService;

	public CarListPanel(String id, String rootFolderPath) {
		super(id);	
		carService = new CarServiceImpl(rootFolderPath);

		CarsDataProvider carsDataProvider = new CarsDataProvider();
		DataView<Car> carDataView = new DataView<Car>("rows", carsDataProvider) {
			@Override
			protected void populateItem(Item<Car> item) {
				final Car car = item.getModelObject();
				item.add(new InvisibleLabel("id", car.getId()));
				item.add(new Label("carModel", car.getCarModel()));
				item.add(new Label("condition", car.getCondition()));
				item.add(new Label("cruisingRange", car.getCruisingRange()));
				item.add(new Label("bodyType", car.getBodyType()));
				item.add(new Label("numberCar", car.getNumberCar()));

				item.add(new InvisibleLink("edit-link") {
					@Override
					public void onClick() {
						setResponsePage(new CarEditPage(car));
					}
				});
				

				item.add(new InvisibleLink("delete-link") {
					@Override
					public void onClick() {
						CarsPage page = new CarsPage();
						try {
							carService.delete(car.getId());
						} catch (PersistenceException e) {
							page.error("The faculty can not be deleted");
						} finally {
							setResponsePage(page);
						}
					}
				});
			}
			
		};
		add(carDataView);
		Label labelID = new Label("label-id", "ID");
		Label labelActions = new Label("label-actions", "Actions");
		labelID.setVisible(AuthorizedSession.get().getRoles().hasRole(UserRole.admin.toString()));
		labelActions.setVisible(AuthorizedSession.get().getRoles().hasRole(UserRole.admin.toString()));
		add(labelID);
		add(labelActions);
	}
	
	private class CarsDataProvider extends SortableDataProvider<Car, Serializable> {

		@Override
		public Iterator<Car> iterator(long first, long count) {
			return carService.getAll().iterator();
		}

		@Override
		public long size() {
			return carService.getAll().size();
		}

		@Override
		public IModel<Car> model(Car object) {
			return new Model(object);
		}
	}


}
