package by.grsu.by.web.page.manager;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;

import by.grsu.by.dataaccess.impl.CarDao;
import by.grsu.by.datamodel.Car;

public class ManagerEditPage extends WebPage {

	private CarDao CarDao;

	private Car Car;

	public ManagerEditPage(Car Car) {
		super();
		this.Car = Car;
		CarDao = new CarDao("testXmlFolder");
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		Form<Car> form = new Form<Car>("form", new CompoundPropertyModel<Car>(Car));
		add(form);

		TextField<String> conditionField = new TextField<String>("condition");
		conditionField.setLabel(new ResourceModel("condition"));
		conditionField.setRequired(true);
		form.add(conditionField);

		TextField<Boolean> characteristicsField = new TextField<Boolean>("characteristics");
		// radiusField.add(RangeValidator.<Double> range(0d, 1_000_000d));
		characteristicsField.setLabel(new ResourceModel("characteristics"));
		characteristicsField.setRequired(true);

		form.add(characteristicsField);

		form.add(new SubmitLink("save") {
			@Override
			public void onSubmit() {
				super.onSubmit();
				CarDao.saveNew(Car);
				setResponsePage(new CarsPage());
			}
		});

		form.add(new Link("cancel") {

			@Override
			public void onClick() {
				setResponsePage(new CarsPage());
			}
		});
		add(new FeedbackPanel("feedback"));
	}

}
