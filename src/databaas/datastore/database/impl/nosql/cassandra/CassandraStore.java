package databaas.datastore.database.impl.nosql.cassandra;

import databaas.datadef.column.type.TypeDefs;
import databaas.datadef.column.type.impl.nosql.cassandra.CassandraTypeDefs;
import databaas.datadef.table.TableDef;
import databaas.dataplace.NoPlaceException;
import databaas.dataplace.PlaceException;
import databaas.dataplace.PlaceInfo;
import databaas.datastore.Table;
import databaas.datastore.database.impl.nosql.NosqlStore;

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
	public boolean setup() {
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

	@Override
	public void engage() throws NoPlaceException, PlaceException {
		
	}
	
}
