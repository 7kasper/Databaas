package databaas.datastore.database.impl.sql.mysql;

import databaas.datastore.database.impl.sql.SqlTable;
import databaas.datatable.TableDef;

public class MysqlTable extends SqlTable {

	public MysqlTable(MysqlStore store, TableDef def) {
		super(store, def);
	}

}
