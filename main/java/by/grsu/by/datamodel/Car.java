package by.grsu.by.datamodel;

import java.io.Serializable;
import java.util.Comparator;

public class Car extends AbstractModel implements Serializable{

	private String carModel;
	private String condition;
	private String cruisingRange;
	private String bodyType;
	private Integer numberCar;


	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
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

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}
	
	public Integer getNumberCar() {
		return numberCar;
	}

	public void setNumberCar(Integer numberCar) {
		this.numberCar = numberCar;
	}
	
	@Override
	public String toString(){
		return getCarModel() + " / " + getBodyType() + " / " + getCruisingRange() + " / " + getCondition() + " / " + getNumberCar();
	}

}
