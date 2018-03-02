package databaas.datastore.impl.sql;

import databaas.database.DBConnect;
import databaas.database.DBType;

public class SqliteStore extends SqlStore {

	public SqliteStore(DBConnect connect) {
		super(connect);
	}

	@Override
	public DBType getDBType() {
		return DBType.SQLITE;
	}

}
