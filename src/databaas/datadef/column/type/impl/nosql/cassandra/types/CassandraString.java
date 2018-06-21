package databaas.datadef.column.type.impl.nosql.cassandra.types;

import databaas.datadef.column.type.impl.nosql.cassandra.CassandraColumnType;

public class CassandraString extends CassandraColumnType {

	private String string;

	public CassandraString() { };

	public CassandraString(String string) {
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
