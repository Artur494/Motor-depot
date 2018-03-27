package by.grsu.by.table;

import java.util.ArrayList;
import java.util.List;

import by.grsu.by.datamodel.Request;

public class RequestTable extends AbstractTable<Request> {
	
	private List<Request> rows;

	@Override
	public List<Request> getRows() {
		if (rows == null) {
			rows = new ArrayList<Request>();
		}
		return rows;
	}

	@Override
	public void setRows(List<Request> rows) {
		this.rows = rows;
	}
}