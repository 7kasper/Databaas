package databaas;

import databaas.database.DBConnect;
import databaas.database.DBType;
import databaas.datastore.DataStore;

public class Databaas {
	
	DBConnect connect;
	private DataStore store;
	
	public Databaas(DBType type, DBConnect connect) {
		this.connect = connect;
		this.store = DataStore.bakeStore(type, connect);
	}
	
	public DataStore getStore() {
		return store;
	}
	
	public DBType getType() {
		return store.getDBType();
	}
	
	
}
