package databaas.datatable.column.type.impl.sql.sqlite;

import java.util.function.Function;

import databaas.datatable.column.type.ColumnType;
import databaas.datatable.column.type.impl.DatabaseTypeDef;

public class SqliteTypeDef extends DatabaseTypeDef {
	
	private static SqliteTypeDef typeDef = new SqliteTypeDef(); 
	private SqliteTypeDef() { }
	public static SqliteTypeDef get() { return typeDef; }
	
	@Override
	public void init() {
		
	}

	@Override
	public Function<Object, ColumnType> getTypeSupplier(Class<?> type) {
		return null;
	}

}
