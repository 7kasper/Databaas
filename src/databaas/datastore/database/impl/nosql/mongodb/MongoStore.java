package databaas.datastore.database.impl.nosql.mongodb;

import databaas.dataplace.PlaceInfo;
import databaas.datastore.database.impl.nosql.NosqlStore;

public class MongoStore extends NosqlStore {

	public MongoStore(PlaceInfo place) {
		super(place);	
	}

	@Override
	public String getType() {
		return "MONGODB";
	}

}
