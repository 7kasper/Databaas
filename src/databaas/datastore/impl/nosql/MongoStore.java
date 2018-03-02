package databaas.datastore.impl.nosql;

import databaas.database.DBConnect;
import databaas.database.DBType;

public class MongoStore extends NosqlStore {

	public MongoStore(DBConnect connect) {
		super(connect);	
	}

	@Override
	public DBType getType() {
		return DBType.MONGODB;
	}

}
