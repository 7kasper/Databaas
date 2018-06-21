package databaas.datadef.column.type.impl.sql.sqlite;

import java.util.function.Function;

import databaas.datadef.column.option.ColumnOption;
import databaas.datadef.column.option.NotNullOption;
import databaas.datadef.column.option.PrimaryKeyOption;
import databaas.datadef.column.type.ColumnOptionType;
import databaas.datadef.column.type.ColumnType;
import databaas.datadef.column.type.impl.DatabaseTypeDefs;
import databaas.datadef.column.type.impl.sql.sqlite.option.SqliteNotNullOption;
import databaas.datadef.column.type.impl.sql.sqlite.option.SqlitePrimaryKeyOption;
import databaas.datadef.column.type.impl.sql.sqlite.types.SqliteInt;
import databaas.datadef.column.type.impl.sql.sqlite.types.SqliteObject;
import databaas.datadef.column.type.impl.sql.sqlite.types.SqliteString;

public class SqliteTypeDefs extends DatabaseTypeDefs {
	
	private static SqliteTypeDefs typeDef = new SqliteTypeDefs(); 
	private SqliteTypeDefs() { }
	public static SqliteTypeDefs get() { return typeDef; }
	
	@Override
	public void init() {
		// = Types = \\
		addTypeDef(Integer.class, SqliteInt.class,	  (integer) -> new SqliteInt(integer));
		addTypeDef(String.class,  SqliteString.class, (string)  -> new SqliteString(string));
		addTypeDef(Object.class,  SqliteObject.class, (object)  -> new SqliteObject(object));
		// = Options = \\
		addOptionDef(PrimaryKeyOption.class, (option) -> new SqlitePrimaryKeyOption(option));
		addOptionDef(NotNullOption.class, 	 (option) -> new SqliteNotNullOption(option));
	}

	@Override
	public Function<Object, ColumnType> getTypeSupplier(Class<?> type) {
		return typeSupplier.getOrDefault(type, (obj) -> {
			return new SqliteObject(obj);
		});
	}

	@Override
	public Function<ColumnOption, ColumnOptionType> getOptionTypeSupplier(Class<? extends ColumnOption> option) {
		return optionSupplier.get(option);
	}

}
