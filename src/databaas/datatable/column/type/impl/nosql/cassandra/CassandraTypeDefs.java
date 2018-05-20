package databaas.datatable.column.type.impl.nosql.cassandra;

import java.util.function.Function;

import databaas.datatable.column.type.ColumnType;
import databaas.datatable.column.type.impl.DatabaseTypeDefs;
import databaas.datatable.column.type.impl.nosql.cassandra.types.CassandraInt;
import databaas.datatable.column.type.impl.nosql.cassandra.types.CassandraObject;
import databaas.datatable.column.type.impl.nosql.cassandra.types.CassandraString;

public class CassandraTypeDefs extends DatabaseTypeDefs {
	
	private static CassandraTypeDefs typeDef = new CassandraTypeDefs(); 
	private CassandraTypeDefs() { }
	public static CassandraTypeDefs get() { return typeDef; }
	
	@Override
	public void init() {
		addTypeDef(String.class, CassandraString.class, (string) -> new CassandraString(string));
		addTypeDef(Integer.class, CassandraInt.class, (integer) -> new CassandraInt(integer));
		addTypeDef(Object.class, CassandraObject.class, (object) -> new CassandraObject(object));
	}

	@Override
	public Function<Object, ColumnType> getTypeSupplier(Class<?> type) {
		return typeSupplier.getOrDefault(type, (obj) -> {
			return new CassandraObject(obj);
		});
	}

}
