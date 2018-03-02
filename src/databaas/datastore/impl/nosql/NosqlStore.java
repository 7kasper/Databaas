package databaas.datastore.impl.nosql;

import databaas.database.DBConnect;
import databaas.datastore.DataStore;

public abstract class NosqlStore implements DataStore {
	
	public final DBConnect connect;
	
	public NosqlStore(DBConnect connect) {
		this.connect = connect;
	}
	
	@Override
	public DBConnect getConnect() {
		return connect;
	}
	
}
