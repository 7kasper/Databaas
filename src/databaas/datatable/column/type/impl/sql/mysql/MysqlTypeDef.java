package databaas.datatable.column.type.impl.sql.mysql;

import java.util.function.Function;

import databaas.datatable.column.type.ColumnType;
import databaas.datatable.column.type.impl.DatabaseTypeDef;

public class MysqlTypeDef extends DatabaseTypeDef {
	
	private static MysqlTypeDef typeDef = new MysqlTypeDef(); 
	private MysqlTypeDef() { }
	public static MysqlTypeDef get() { return typeDef; }
	
	@Override
	public void init() {
		
	}

	@Override
	public Function<Object, ColumnType> getTypeSupplier(Class<?> type) {
		return null;
	}

}
