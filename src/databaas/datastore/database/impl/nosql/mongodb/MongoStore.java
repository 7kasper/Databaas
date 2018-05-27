package databaas.datastore.database.impl.nosql.mongodb;

import databaas.dataplace.PlaceInfo;
import databaas.datastore.database.impl.nosql.NosqlStore;
import databaas.datatable.TableDef;
import databaas.datatable.column.type.TypeDefs;
import databaas.datatable.column.type.impl.nosql.mongodb.MongoTypeDefs;

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
	public boolean validate() {
		return false;
	}

	@Override
	public boolean createTable(TableDef table) {
		return false;
	}

}
