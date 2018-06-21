package databaas.datadef.column.type.impl.sql.mysql;

import java.util.function.Function;

import databaas.datadef.column.option.ColumnOption;
import databaas.datadef.column.type.ColumnOptionType;
import databaas.datadef.column.type.ColumnType;
import databaas.datadef.column.type.impl.DatabaseTypeDefs;
import databaas.datadef.column.type.impl.sql.mysql.types.MysqlObject;

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
