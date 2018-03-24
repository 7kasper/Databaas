package databaas.datastore.database.impl.sql.mysql;

import databaas.dataplace.PlaceInfo;
import databaas.datastore.database.impl.sql.SqlStore;
import databaas.datatable.column.type.TypeDef;
import databaas.datatable.column.type.impl.sql.mysql.MysqlTypeDef;

public class MysqlStore extends SqlStore {
	
	TypeDef typeDef = MysqlTypeDef.get();
	
	public MysqlStore(PlaceInfo place) {
		super(place);
	}

	@Override
	public String getType() {
		return "MYSQL";
	}

	@Override
	public TypeDef getTypeDef() {
		return typeDef;
	}

}
