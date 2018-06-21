package databaas.datastore.database.impl.sql.sqlite;

import databaas.DatabaasLogger;
import databaas.datadef.column.type.TypeDefs;
import databaas.datadef.column.type.impl.sql.sqlite.SqliteTypeDefs;
import databaas.datadef.table.TableDef;
import databaas.dataplace.PlaceException;
import databaas.dataplace.NoPlaceException;
import databaas.dataplace.PlaceInfo;
import databaas.datastore.Table;
import databaas.dataplace.database.impl.DatabaseConnectPlace;
import databaas.datastore.database.impl.sql.SqlStore;

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
	public boolean setup() {
		typeDefs.init();
		try {
			engage();
		} catch (NoPlaceException | PlaceException e) {
			DatabaasLogger.log(e, "Error while validating store!");
			return false;
		}
		return true;
	}

	@Override
	public Table getTable(TableDef def) {
		return new SqliteTable(this, def);
	}

}
