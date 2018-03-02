package databaas.datastore.impl.nosql;

import databaas.database.DBConnect;
import databaas.database.DBType;

public class CassandraStore extends NosqlStore {

	public CassandraStore(DBConnect connect) {
		super(connect);
	}

	@Override
	public DBType getDBType() {
		return DBType.CASSANDRA;
	}

}
