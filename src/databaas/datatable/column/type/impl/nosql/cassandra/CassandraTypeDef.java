package databaas.datatable.column.type.impl.nosql.cassandra;

import java.util.function.Function;

import databaas.datatable.column.type.ColumnType;
import databaas.datatable.column.type.impl.DatabaseTypeDef;
import databaas.datatable.column.type.impl.nosql.cassandra.types.CassandraObject;
import databaas.datatable.column.type.impl.nosql.cassandra.types.CassandraString;

public class CassandraTypeDef extends DatabaseTypeDef {
	
	private static CassandraTypeDef typeDef = new CassandraTypeDef(); 
	private CassandraTypeDef() { }
	public static CassandraTypeDef get() { return typeDef; }
	
	@Override
	public void init() {
		addTypeDef(String.class, CassandraString.class, (string) -> new CassandraString());
	}

	@Override
	public Function<Object, ColumnType> getTypeSupplier(Class<?> type) {
		return typeSupplier.getOrDefault(type, (obj) -> {
			return new CassandraObject();
		});
	}

}
