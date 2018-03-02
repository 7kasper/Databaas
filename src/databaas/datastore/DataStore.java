package databaas.datastore;

import databaas.database.DBType;
import databaas.datastore.impl.SqliteStore;

public interface DataStore {
	
	public static DataStore bakeStore(DBType type) {
		switch(type) {
		case CASSANDRA:
			break;
		case MONGODB:
			break;
		case MYSQL:
			break;
		case SQLITE:
		default:
			return new SqliteStore();
		}
	}
}
