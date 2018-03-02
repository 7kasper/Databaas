package databaas.datastore;

import databaas.database.DBConnect;
import databaas.database.DBType;
import databaas.datastore.impl.nosql.CassandraStore;
import databaas.datastore.impl.nosql.MongoStore;
import databaas.datastore.impl.sql.MysqlStore;
import databaas.datastore.impl.sql.SqliteStore;

public interface DataStore {
	
	/**
	 * @return the {@link DBConnect} belonging to this DataStore.
	 */
	public DBConnect getConnect();
	
	/**
	 * @return the {@link DBType} belonging to this DataStore.
	 */
	public DBType getType();
	
	/**
	 * Creates a new DataStore able to access {@link DBType} type using {@link DBConnect} connect
	 * @param type
	 * @param connect
	 * @return the new DataStore implementation.
	 */
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
