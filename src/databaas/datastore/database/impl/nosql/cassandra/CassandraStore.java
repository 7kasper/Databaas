package databaas.datastore.database.impl.nosql.cassandra;

import databaas.dataplace.PlaceInfo;
import databaas.datastore.database.impl.nosql.NosqlStore;

public class CassandraStore extends NosqlStore {

	public CassandraStore(PlaceInfo place) {
		super(place);
	}

	@Override
	public String getType() {
		return "CASSANDRA";
	}

}
