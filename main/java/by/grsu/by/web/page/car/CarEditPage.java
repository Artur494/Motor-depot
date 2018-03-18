package by.grsu.by.web.page.car;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;

import by.grsu.by.dataaccess.impl.CarDao;
import by.grsu.by.datamodel.Car;
import by.grsu.by.datamodel.CarModels;
import by.grsu.by.datamodel.Characteristics;

public class CarEditPage extends WebPage {

	private CarDao carDao;
	private Boolean selectCreateOrUpdate = true;
	private Car car;

	public CarEditPage(Car car) {
		super();
		this.car = car;
		carDao = new CarDao("testXmlFolder");
	}
	
	public CarEditPage(Car car, Boolean selectCreateOrUpdate) {
		super();
		this.car = car;
		this.selectCreateOrUpdate = selectCreateOrUpdate;
		carDao = new CarDao("testXmlFolder");
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		Form<Car> form = new Form<Car>("form", new CompoundPropertyModel<Car>(car));
		add(form);
		
		final DropDownChoice<String> dropModels = new DropDownChoice<String>("carModel", CarModels.getCarModels());
		dropModels.setLabel(new Model<String>("Model"));
		dropModels.setRequired(true);
		form.add(dropModels);

		final List<String> conditions = Arrays.asList(new String[] {"Ready for flight", "Requires refueling", "Need repair" });
		final DropDownChoice<String> dropChoice = new DropDownChoice<String>("condition", conditions);
		dropChoice.setLabel(new Model<String>("Condition"));
		dropChoice.setRequired(true);
		form.add(dropChoice);
		
		final DropDownChoice<String> dropCharacteristicsCruisingRange = new DropDownChoice<String>("cruisingRange", Characteristics.getCruisingRange());
		dropCharacteristicsCruisingRange.setLabel(new Model<String>("Cruising Range"));
		dropCharacteristicsCruisingRange.setRequired(true);
		form.add(dropCharacteristicsCruisingRange);
		
		final DropDownChoice<String> dropCharacteristicsBodyType = new DropDownChoice<String>("bodyType", Characteristics.getBodyType());
		dropCharacteristicsBodyType.setLabel(new Model<String>("Body type"));
		dropCharacteristicsBodyType.setRequired(true);
		form.add(dropCharacteristicsBodyType);
		
		TextField<Integer> nameField = new TextField<Integer>("numberCar");
		nameField.setLabel(new Model<String>("Number Car"));
		nameField.setRequired(true);
		form.add(nameField);
        
		 
		form.add(new SubmitLink("save") {
			@Override
			public void onSubmit() {
				super.onSubmit();
				if (selectCreateOrUpdate) {
					
				carDao.saveNew(car);
				}
				else {carDao.update(car);}
				setResponsePage(new CarsPage());
			}
		});

		form.add(new Link<Object>("cancel") {

			@Override
			public void onClick() {
				setResponsePage(new CarsPage());
			}
		});
		add(new FeedbackPanel("feedback"));
	}

}
