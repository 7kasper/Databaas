package databaas.datatable.column.type.impl.nosql.cassandra.types;

import databaas.datatable.column.type.impl.nosql.cassandra.CassandraColumnType;

public class CassandraInt extends CassandraColumnType {

	private Integer integer;

	public CassandraInt() { }

	public CassandraInt(int integer) {
		this.integer = integer;
	}

	@Override
	public Integer toType() {
		return integer;
	}

	@Override
	public String getQueryName() {
		return "int";
	}

}
