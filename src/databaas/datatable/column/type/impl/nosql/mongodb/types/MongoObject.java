package databaas.datatable.column.type.impl.nosql.mongodb.types;

import databaas.datatable.column.type.impl.nosql.mongodb.MongoColumnType;

public class MongoObject extends MongoColumnType{

	private Object obj;

	public MongoObject() { }

	public MongoObject(Object obj) {
		this.obj = obj;
	}

	@Override
	public Object toType() {
		return obj;
	}

	@Override
	public String getQueryName() {
		return "object";
	}

}
