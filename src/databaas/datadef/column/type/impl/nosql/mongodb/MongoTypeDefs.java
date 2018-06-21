package databaas.datadef.column.type.impl.nosql.mongodb;

import java.util.function.Function;

import databaas.datadef.column.option.ColumnOption;
import databaas.datadef.column.type.ColumnOptionType;
import databaas.datadef.column.type.ColumnType;
import databaas.datadef.column.type.impl.DatabaseTypeDefs;
import databaas.datadef.column.type.impl.nosql.mongodb.types.MongoObject;

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

	@Override
	public Function<ColumnOption, ColumnOptionType> getOptionTypeSupplier(Class<? extends ColumnOption> option) {
		return optionSupplier.get(option);
	}

}
