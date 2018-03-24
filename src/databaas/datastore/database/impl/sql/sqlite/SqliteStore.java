package databaas.datastore.database.impl.sql.sqlite;

import databaas.dataplace.PlaceInfo;
import databaas.datastore.database.impl.sql.SqlStore;
import databaas.datatable.column.type.TypeDef;
import databaas.datatable.column.type.impl.sql.sqlite.SqliteTypeDef;

public class SqliteStore extends SqlStore {
	
	TypeDef typeDef = SqliteTypeDef.get();
	
	public SqliteStore(PlaceInfo place) {
		super(place);
	}

	@Override
	public String getType() {
		return "SQLITE";
	}

	@Override
	public TypeDef getTypeDef() {
		return typeDef;
	}

}
