package by.grsu.by.datamodel;

import java.util.Date;

public class Flight extends AbstractModel {
	
	private Date date;
	private String status;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
