package databaas.datatable.column.type.impl.sql.sqlite.types;

import databaas.datatable.column.type.impl.sql.sqlite.SqliteColumnType;

public class SqliteInt extends SqliteColumnType {

	private int i;

	public SqliteInt() { }

	public SqliteInt(int i) {
		this.i = i;
	}

	@Override
	public Object toType() {
		return i;
	}

	@Override
	public String getQueryName() {
		return "INTEGER";
	}

}
