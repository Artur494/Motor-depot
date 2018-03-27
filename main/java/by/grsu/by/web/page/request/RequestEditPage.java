package by.grsu.by.web.page.request;

import java.util.Date;

import org.apache.wicket.datetime.StyleDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
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
import by.grsu.by.service.Service;
import by.grsu.by.service.impl.RequestServiceImpl;
import by.grsu.by.web.page.AbstractPage;

public class RequestEditPage extends AbstractPage {

	private Service requestService;
	public Request flightRequest;
	

	public RequestEditPage() {
		super();
		this.flightRequest = new Request();
		requestService = new RequestServiceImpl("testXmlFolder");
	}
	
	public RequestEditPage(Request request) {
		super();
		this.flightRequest = request;
		requestService = new RequestServiceImpl("testXmlFolder");
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		Form<Request> form = new Form<Request>("form", new CompoundPropertyModel<Request>(flightRequest));
		add(form);
		
		flightRequest.setStatus("In processing");
		
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
		dateTextField.setLabel(new Model<String>("Date"));
		dateTextField.setRequired(true);
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
				requestService.saveOrUpdate(flightRequest);
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
