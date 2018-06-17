package databaas.datastore.database.impl.sql.sqlite;

import databaas.dataplace.PlaceInfo;
import databaas.datastore.Table;
import databaas.dataplace.database.impl.DatabaseConnectPlace;
import databaas.datastore.database.impl.sql.SqlStore;
import databaas.datatable.TableDef;
import databaas.datatable.column.type.TypeDefs;
import databaas.datatable.column.type.impl.sql.sqlite.SqliteTypeDefs;

public class SqliteStore extends SqlStore {
	
	TypeDefs typeDefs = SqliteTypeDefs.get();
	
	public SqliteStore(PlaceInfo place) {
		super(place);
	}

	@Override
	public String getType() {
		return "SQLITE";
	}

	@Override
	public String getJDBCurl(DatabaseConnectPlace dcp) {
		return dcp.getUrl() + "/" +  getConnect().getPlaceName() + ".db";
	}

	@Override
	public TypeDefs getTypeDefs() {
		return typeDefs;
	}

	@Override
	public boolean validate() {
		typeDefs.init();
		return exec(null);
	}

	@Override
	public Table getTable(TableDef def) {
		return new SqliteTable(this, def);
	}

}
