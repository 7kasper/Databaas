package databaas.datatable.column.type.impl.sql.sqlite;

import java.util.function.Function;

import databaas.datatable.column.type.ColumnType;
import databaas.datatable.column.type.impl.DatabaseTypeDefs;
import databaas.datatable.column.type.impl.sql.sqlite.types.SqliteObject;
import databaas.datatable.column.type.impl.sql.sqlite.types.SqliteString;

public class SqliteTypeDefs extends DatabaseTypeDefs {
	
	private static SqliteTypeDefs typeDef = new SqliteTypeDefs(); 
	private SqliteTypeDefs() { }
	public static SqliteTypeDefs get() { return typeDef; }
	
	@Override
	public void init() {
		addTypeDef(String.class, SqliteString.class, (string) -> new SqliteString(string));
		addTypeDef(Object.class, SqliteObject.class, (object) -> new SqliteObject(object));
	}

	@Override
	public Function<Object, ColumnType> getTypeSupplier(Class<?> type) {
		return typeSupplier.getOrDefault(type, (obj) -> {
			return new SqliteObject(obj);
		});
	}

}
