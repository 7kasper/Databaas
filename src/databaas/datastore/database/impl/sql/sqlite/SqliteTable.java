package databaas.datastore.database.impl.sql.sqlite;

import databaas.datadef.table.TableDef;
import databaas.datastore.database.impl.sql.SqlTable;

public class SqliteTable extends SqlTable {

	public SqliteTable(SqliteStore store, TableDef def) {
		super(store, def);
	}

}
