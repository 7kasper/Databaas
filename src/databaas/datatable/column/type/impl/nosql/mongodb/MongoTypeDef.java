package databaas.datatable.column.type.impl.nosql.mongodb;

import java.util.function.Function;

import databaas.datatable.column.type.ColumnType;
import databaas.datatable.column.type.impl.DatabaseTypeDef;

public class MongoTypeDef extends DatabaseTypeDef {
	
	private static MongoTypeDef typeDef = new MongoTypeDef(); 
	private MongoTypeDef() { }
	public static MongoTypeDef get() { return typeDef; }
	
	@Override
	public void init() {
		
	}
	
	@Override
	public Function<Object, ColumnType> getTypeSupplier(Class<?> type) {
		return null;
	}
	
}
