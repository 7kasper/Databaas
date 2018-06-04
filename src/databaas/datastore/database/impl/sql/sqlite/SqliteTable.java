package databaas.datastore.database.impl.sql.sqlite;

import databaas.datastore.database.impl.sql.SqlTable;
import databaas.datatable.TableDef;

public class SqliteTable extends SqlTable {

	public SqliteTable(SqliteStore store, TableDef def) {
		super(store, def);
	}

}
