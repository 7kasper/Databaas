package databaas.datastore.database.impl.sql.sqlite;

import databaas.dataplace.PlaceInfo;
import databaas.datastore.database.impl.sql.SqlStore;

public class SqliteStore extends SqlStore {

	public SqliteStore(PlaceInfo place) {
		super(place);
	}

	@Override
	public String getType() {
		return "SQLITE";
	}

}
