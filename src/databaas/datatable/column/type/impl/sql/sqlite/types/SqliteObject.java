package databaas.datatable.column.type.impl.sql.sqlite.types;

import databaas.datatable.column.type.impl.sql.sqlite.SqliteColumnType;

public class SqliteObject extends SqliteColumnType {

	private Object obj;

	public SqliteObject() { }

	public SqliteObject(Object obj) {
		this.obj = obj;
	}

	@Override
	public Object toType() {
		return obj;
	}

	@Override
	public String getQueryName() {
		return "blob";
	}

}
