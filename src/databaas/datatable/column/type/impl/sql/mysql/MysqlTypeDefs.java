package databaas.datatable.column.type.impl.sql.mysql;

import java.util.function.Function;

import databaas.datatable.column.type.ColumnType;
import databaas.datatable.column.type.impl.DatabaseTypeDefs;

public class MysqlTypeDefs extends DatabaseTypeDefs {
	
	private static MysqlTypeDefs typeDef = new MysqlTypeDefs(); 
	private MysqlTypeDefs() { }
	public static MysqlTypeDefs get() { return typeDef; }
	
	@Override
	public void init() {
		
	}

	@Override
	public Function<Object, ColumnType> getTypeSupplier(Class<?> type) {
		return null;
	}

}
