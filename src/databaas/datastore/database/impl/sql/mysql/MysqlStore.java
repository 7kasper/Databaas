package databaas.datastore.database.impl.sql.mysql;

import databaas.dataplace.PlaceInfo;
import databaas.dataplace.database.impl.DatabaseConnectPlace;
import databaas.datastore.database.impl.sql.SqlStore;
import databaas.datatable.column.type.TypeDefs;
import databaas.datatable.column.type.impl.sql.mysql.MysqlTypeDefs;

public class MysqlStore extends SqlStore {

	TypeDefs typeDefs = MysqlTypeDefs.get();

	public MysqlStore(PlaceInfo place) {
		super(place);
	}

	@Override
	public String getType() {
		return "MYSQL";
	}

	@Override
	public String getJDBCurl(DatabaseConnectPlace dcp) {
		return "TODO"; //TODO do.
	}

	@Override
	public TypeDefs getTypeDefs() {
		return typeDefs;
	}

	@Override
	public boolean validate() {
		return false;
	}

}
