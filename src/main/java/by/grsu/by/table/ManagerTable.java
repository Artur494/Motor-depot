package by.grsu.by.table;

import java.util.ArrayList;
import java.util.List;

import by.grsu.by.datamodel.Manager;

public class ManagerTable extends AbstractTable<Manager> {
	
	private List<Manager> rows;

	@Override
	public List<Manager> getRows() {
		if (rows == null) {
			rows = new ArrayList<Manager>();
		}
		return rows;
	}

	@Override
	public void setRows(List<Manager> rows) {
		this.rows = rows;
	}

}
