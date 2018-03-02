package databaas.datastore.impl.sql;

import databaas.database.DBConnect;
import databaas.database.DBType;

public class MysqlStore extends SqlStore {

	public MysqlStore(DBConnect connect) {
		super(connect);
	}

	@Override
	public DBType getType() {
		return DBType.MYSQL;
	}

}
