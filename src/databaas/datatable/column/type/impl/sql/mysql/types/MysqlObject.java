package databaas.datatable.column.type.impl.sql.mysql.types;

import databaas.datatable.column.type.impl.sql.mysql.MysqlColumnType;

public class MysqlObject extends MysqlColumnType {

	private Object obj;

	public MysqlObject() { };

	public MysqlObject(Object obj) {
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
