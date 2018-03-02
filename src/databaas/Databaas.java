package databaas;

import databaas.database.DBConnect;
import databaas.database.DBType;
import databaas.datastore.DataStore;

public class Databaas {
	
	DBConnect connect;
	
	public Databaas(DBType type, DBConnect connect) {
		this.connect = connect;
	}
	
	private DataStore store;
	
	public DBType getType() {
		return type;
	}
}
