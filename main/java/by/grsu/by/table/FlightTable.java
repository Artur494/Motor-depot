package by.grsu.by.table;

import java.util.ArrayList;
import java.util.List;

import by.grsu.by.datamodel.Flight;

public class FlightTable extends AbstractTable<Flight> {
	
	private List<Flight> rows;

	@Override
	public List<Flight> getRows() {
		if (rows == null) {
			rows = new ArrayList<Flight>();
		}
		return rows;
	}

	@Override
	public void setRows(List<Flight> rows) {
		this.rows = rows;
	}

}
