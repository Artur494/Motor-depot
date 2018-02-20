package by.grsu.by.datamodel;

import java.util.List;

public class Driver extends AbstractModel {
	private Car car;
	private List<Flight> flights;
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	public List<Flight> getFlights() {
		return flights;
	}
	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

}
