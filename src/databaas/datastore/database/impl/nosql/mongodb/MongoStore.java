package databaas.datastore.database.impl.nosql.mongodb;

import databaas.dataplace.PlaceInfo;
import databaas.datastore.database.impl.nosql.NosqlStore;
import databaas.datatable.column.type.TypeDef;
import databaas.datatable.column.type.impl.nosql.mongodb.MongoTypeDef;

public class MongoStore extends NosqlStore {

	TypeDef typeDef = MongoTypeDef.get();
	
	public MongoStore(PlaceInfo place) {
		super(place);	
	}

	@Override
	public String getType() {
		return "MONGODB";
	}

	@Override
	public TypeDef getTypeDef() {
		return typeDef;
	}

}
