package by.grsu.by.datamodel;

import java.io.Serializable;

public class Car extends AbstractModel implements Serializable {
	
	private String condition;
	private Boolean characteristics;
	
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public Boolean getCharacteristics() {
		return characteristics;
	}
	public void setCharacteristics(Boolean characteristics) {
		this.characteristics = characteristics;
	}	

}
