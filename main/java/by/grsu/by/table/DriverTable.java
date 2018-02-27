package by.grsu.by.table;

import java.util.ArrayList;
import java.util.List;

import by.grsu.by.datamodel.Driver;

public class DriverTable extends AbstractTable<Driver> {

	private List<Driver> rows;

	@Override
	public List<Driver> getRows() {
		if (rows == null) {
			rows = new ArrayList<Driver>();
		}
		return rows;
	}

	@Override
	public void setRows(List<Driver> rows) {
		this.rows = rows;
	}
}
