package databaas.datadef.column.type.impl.sql.sqlite.types;

import databaas.datadef.column.type.impl.sql.sqlite.SqliteColumnType;

public class SqliteString extends SqliteColumnType {

	private String string;

	public SqliteString() { }

	public SqliteString(String string) {
		this.string = string;
	}

	@Override
	public String toType() {
		return string;
	}

	@Override
	public String getQueryName() {
		return "text";
	}

}
