package databaas.datastore.database.impl.sql.mysql;

import databaas.datadef.column.type.TypeDefs;
import databaas.datadef.column.type.impl.sql.mysql.MysqlTypeDefs;
import databaas.datadef.table.TableDef;
import databaas.dataplace.PlaceInfo;
import databaas.datastore.Table;
import databaas.dataplace.database.impl.DatabaseConnectPlace;
import databaas.datastore.database.impl.sql.SqlStore;

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
	public boolean setup() {
		return false;
	}

	@Override
	public Table getTable(TableDef def) {
		return new MysqlTable(this, def);
	}

}
