package databaas.datastore.database.impl.sql;

import databaas.datadef.table.TableDef;
import databaas.datastore.database.impl.DatabaseTable;

public abstract class SqlTable extends DatabaseTable {

	public SqlTable(SqlStore store, TableDef def) {
		super(store, def);
	}

}
