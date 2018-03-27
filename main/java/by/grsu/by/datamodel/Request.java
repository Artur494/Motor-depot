package by.grsu.by.datamodel;

import java.io.Serializable;
import java.util.Date;

public class Request extends AbstractModel implements Serializable {

	
	private Date date;
	private String condition;
	private String cruisingRange;
	private String bodyType;
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date dateCreate) {
		this.date = dateCreate;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getCruisingRange() {
		return cruisingRange;
	}

	public void setCruisingRange(String cruisingRange) {
		this.cruisingRange = cruisingRange;
	}

	public String getBodyType() {
		return bodyType;
	}

	public void setBodyType(String bodyType) {
		this.bodyType = bodyType;
	}

}
