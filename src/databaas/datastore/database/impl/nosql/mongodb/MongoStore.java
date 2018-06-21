package databaas.datastore.database.impl.nosql.mongodb;

import databaas.datadef.column.type.TypeDefs;
import databaas.datadef.column.type.impl.nosql.mongodb.MongoTypeDefs;
import databaas.datadef.table.TableDef;
import databaas.dataplace.NoPlaceException;
import databaas.dataplace.PlaceException;
import databaas.dataplace.PlaceInfo;
import databaas.datastore.Table;
import databaas.datastore.database.impl.nosql.NosqlStore;

public class MongoStore extends NosqlStore {

	TypeDefs typeDefs = MongoTypeDefs.get();
	
	public MongoStore(PlaceInfo place) {
		super(place);	
	}

	@Override
	public String getType() {
		return "MONGODB";
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
		return new MongoTable(this, def);
	}

	@Override
	public void engage() throws NoPlaceException, PlaceException {
		
	}

}
