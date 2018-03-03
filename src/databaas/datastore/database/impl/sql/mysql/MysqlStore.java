package databaas.datastore.database.impl.sql.mysql;

import databaas.dataplace.PlaceInfo;
import databaas.datastore.database.impl.sql.SqlStore;

public class MysqlStore extends SqlStore {

	public MysqlStore(PlaceInfo place) {
		super(place);
	}

	@Override
	public String getType() {
		return "MYSQL";
	}

}
