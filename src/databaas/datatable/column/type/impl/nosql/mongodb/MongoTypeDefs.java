package databaas.datatable.column.type.impl.nosql.mongodb;

import java.util.function.Function;

import databaas.datatable.column.type.ColumnType;
import databaas.datatable.column.type.impl.DatabaseTypeDefs;
import databaas.datatable.column.type.impl.nosql.mongodb.types.MongoObject;

public class MongoTypeDefs extends DatabaseTypeDefs {

	private static MongoTypeDefs typeDef = new MongoTypeDefs(); 
	private MongoTypeDefs() { }
	public static MongoTypeDefs get() { return typeDef; }

	@Override
	public void init() {

	}

	@Override
	public Function<Object, ColumnType> getTypeSupplier(Class<?> type) {
		return typeSupplier.getOrDefault(type, (obj) -> {
			return new MongoObject(obj);
		});
	}

}
