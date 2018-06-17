package databaas.datastore.database.impl.nosql.cassandra;

import databaas.dataplace.PlaceInfo;
import databaas.datastore.Table;
import databaas.datastore.database.impl.nosql.NosqlStore;
import databaas.datatable.TableDef;
import databaas.datatable.column.type.TypeDefs;
import databaas.datatable.column.type.impl.nosql.cassandra.CassandraTypeDefs;

public class CassandraStore extends NosqlStore {
	
	TypeDefs typeDefs = CassandraTypeDefs.get();
	
	public CassandraStore(PlaceInfo place) {
		super(place);
	}
	
	@Override
	public String getType() {
		return "CASSANDRA";
	}
	
	@Override
	public TypeDefs getTypeDefs() {
		return typeDefs;
	}

	@Override
	public boolean validate() {
		return false;
	}

	@Override
	public Table createTable(TableDef table) {
		return null;
	}

	@Override
	public Table getTable(TableDef def) {
		return new CassandraTable(this, def);
	}
	
}
