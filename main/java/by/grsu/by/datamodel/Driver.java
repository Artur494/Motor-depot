package by.grsu.by.datamodel;

import java.io.Serializable;
import java.util.List;

public class Driver extends AbstractModel implements Serializable {
	private Car car;
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	
	@Override
	public String toString(){
		return car.getCarModel() + " / " + car.getBodyType()  + " / " + car.getCruisingRange() + " / " + car.getCondition() + " / " + car.getNumberCar();
	}

}
