package databaas.datatable.column.type.impl.sql.mysql;

import java.util.function.Function;

import databaas.datatable.column.option.ColumnOption;
import databaas.datatable.column.type.ColumnOptionType;
import databaas.datatable.column.type.ColumnType;
import databaas.datatable.column.type.impl.DatabaseTypeDefs;
import databaas.datatable.column.type.impl.sql.mysql.types.MysqlObject;

public class MysqlTypeDefs extends DatabaseTypeDefs {
	
	private static MysqlTypeDefs typeDef = new MysqlTypeDefs(); 
	private MysqlTypeDefs() { }
	public static MysqlTypeDefs get() { return typeDef; }
	
	@Override
	public void init() {
		
	}

	@Override
	public Function<Object, ColumnType> getTypeSupplier(Class<?> type) {
		return typeSupplier.getOrDefault(type, (obj) -> {
			return new MysqlObject(obj);
		});
	}

	@Override
	public Function<ColumnOption, ColumnOptionType> getOptionTypeSupplier(Class<? extends ColumnOption> option) {
		return optionSupplier.get(option);
	}

}
