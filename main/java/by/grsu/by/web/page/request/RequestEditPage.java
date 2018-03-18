package by.grsu.by.web.page.request;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.wicket.datetime.StyleDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import by.grsu.by.dataaccess.impl.RequestDao;
import by.grsu.by.datamodel.Characteristics;
import by.grsu.by.datamodel.Request;

public class RequestEditPage extends WebPage {

	private RequestDao requestDao;
	private Boolean selectCreateOrUpdate = true;
	public Request flightRequest;
	

	public RequestEditPage(Request request, Boolean selectCreateOrUpdate) {
		super();
		this.flightRequest = request;
		requestDao = new RequestDao("testXmlFolder");
		this.selectCreateOrUpdate = selectCreateOrUpdate;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		Form<Request> form = new Form<Request>("form", new CompoundPropertyModel<Request>(flightRequest));
		add(form);
		
		
		final List<String> status = Arrays.asList(new String[] {"In processing", "Complited", "Refusing" });
		final DropDownChoice<String> dropStatus = new DropDownChoice<String>("status", status);
		dropStatus.setLabel(new Model<String>("Status"));
		dropStatus.setRequired(true);
		form.add(dropStatus);

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
		
		
		DateTextField dateTextField = new DateTextField("dateTextField1", new PropertyModel<Date>(this, "flightRequest.date"),
				new StyleDateConverter("S-", true));
		form.add(dateTextField);

		DatePicker datePicker = new DatePicker() {
			@Override
			protected String getAdditionalJavaScript() {
				return "${calendar}.cfg.setProperty(\"navigator\",true,false); ${calendar}.render();";
			}
		};

		datePicker.setShowOnFieldClick(true);
		datePicker.setAutoHide(true);
		dateTextField.add(datePicker);      
		 
		form.add(new SubmitLink("save") {
			@Override
			public void onSubmit() {
				super.onSubmit();
				if (selectCreateOrUpdate) {			
					requestDao.saveNew(flightRequest);
				}
				else {requestDao.update(flightRequest);}
				setResponsePage(new RequestPage());
			}
		});

		form.add(new Link<Object>("cancel") {

			@Override
			public void onClick() {
				setResponsePage(new RequestPage());
			}
		});
		add(new FeedbackPanel("feedback"));
	}

}
