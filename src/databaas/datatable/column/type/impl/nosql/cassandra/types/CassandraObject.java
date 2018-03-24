package databaas.datatable.column.type.impl.nosql.cassandra.types;

import databaas.datatable.column.type.impl.nosql.cassandra.CassandraColumnType;

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
	
}