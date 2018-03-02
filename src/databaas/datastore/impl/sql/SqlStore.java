package databaas.datastore.impl.sql;

import databaas.database.DBConnect;
import databaas.datastore.DataStore;

public abstract class SqlStore implements DataStore {

	private final DBConnect connect;
	
	public SqlStore(DBConnect connect) {
		this.connect = connect;
	}
	
	@Override
	public DBConnect getConnect() {
		return connect;
	}
	
}
