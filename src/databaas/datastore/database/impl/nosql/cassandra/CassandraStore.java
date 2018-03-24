package databaas.datastore.database.impl.nosql.cassandra;

import databaas.dataplace.PlaceInfo;
import databaas.datastore.database.impl.nosql.NosqlStore;
import databaas.datatable.column.type.TypeDef;
import databaas.datatable.column.type.impl.nosql.cassandra.CassandraTypeDef;

public class CassandraStore extends NosqlStore {
	
	TypeDef typeDef = CassandraTypeDef.get();
	
	public CassandraStore(PlaceInfo place) {
		super(place);
	}
	
	@Override
	public String getType() {
		return "CASSANDRA";
	}
	
	@Override
	public TypeDef getTypeDef() {
		return typeDef;
	}
	
}
