package by.grsu.by.datamodel;

import java.io.Serializable;

public class Request extends AbstractModel implements Serializable {
	
	private String conditionCar;
	private Boolean performanceCar;
	
	public String getConditionCar() {
		return conditionCar;
	}
	public void setConditionCar(String conditionCar) {
		this.conditionCar = conditionCar;
	}
	public Boolean getPerformanceCar() {
		return performanceCar;
	}
	public void setPerformanceCar(Boolean performanceCar) {
		this.performanceCar = performanceCar;
	}
	

}
