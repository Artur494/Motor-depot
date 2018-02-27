package by.grsu.by.table;

import java.util.ArrayList;
import java.util.List;

import by.grsu.by.datamodel.Car;


public class CarTable extends AbstractTable<Car> {

	private List<Car> rows;
	
	@Override
	public List<Car> getRows() {
		if (rows == null) {
			rows = new ArrayList<Car>();
		}
		return rows;
	}

	@Override
	public void setRows(List<Car> rows) {
		this.rows = rows;
	}

}
