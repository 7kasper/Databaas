package databaas.datastore;

import databaas.database.DBConnect;
import databaas.database.DBType;
import databaas.datastore.impl.nosql.CassandraStore;
import databaas.datastore.impl.nosql.MongoStore;
import databaas.datastore.impl.sql.MysqlStore;
import databaas.datastore.impl.sql.SqliteStore;

public interface DataStore {
	
	public DBConnect getConnect();
	
	public DBType getDBType();
	
	public static DataStore bakeStore(DBType type, DBConnect connect) {
		switch(type) {
			case CASSANDRA:
				return new CassandraStore(connect);
			case MONGODB:
				return new MongoStore(connect);
			case MYSQL:
				return new MysqlStore(connect);
			default: case SQLITE:
				return new SqliteStore(connect);
		}
	}
}
