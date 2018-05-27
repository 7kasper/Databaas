package databaas.datastore.database.impl.sql.sqlite;

import java.sql.SQLException;

import databaas.DatabaasLogger;
import databaas.dataplace.NoPlaceException;
import databaas.dataplace.PlaceInfo;
import databaas.datastore.database.impl.sql.SqlStore;
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
	public TypeDefs getTypeDefs() {
		return typeDefs;
	}

	@Override
	public boolean validate() {
		try {
			exec((con) -> {});
		} catch (NoPlaceException | SQLException e) {
			DatabaasLogger.log(e, "Error while executing sql function!");
		}
		return false;
	}

}
