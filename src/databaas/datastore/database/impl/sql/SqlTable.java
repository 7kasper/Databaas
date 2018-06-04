package databaas.datastore.database.impl.sql;

import databaas.datastore.database.impl.DatabaseTable;
import databaas.datatable.TableDef;

public abstract class SqlTable extends DatabaseTable {

	public SqlTable(SqlStore store, TableDef def) {
		super(store, def);
	}

}
