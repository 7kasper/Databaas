package databaas.datadef.row;

import databaas.datadef.table.TableDef;

public class Row {

	private final TableDef tabledef;
	private Object[] values;

	public Row(TableDef tabledef, Object... values) {
		this.tabledef = tabledef;
		this.values = values;
	}

	public void setValue(int column, Object value) {
		values[column] = value;
	}

	public void setValue(String columnName, Object value) {
		tabledef.getColumn("");
	}
}
