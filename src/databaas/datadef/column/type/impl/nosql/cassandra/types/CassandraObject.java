package databaas.datadef.column.type.impl.nosql.cassandra.types;

import databaas.datadef.column.type.impl.nosql.cassandra.CassandraColumnType;

public class CassandraObject extends CassandraColumnType {
	
	private Object obj;
	
	public CassandraObject() { };
	
	public CassandraObject(Object obj) {
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