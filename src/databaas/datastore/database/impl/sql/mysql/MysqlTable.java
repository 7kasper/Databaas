package databaas.datastore.database.impl.sql.mysql;

import databaas.datadef.table.TableDef;
import databaas.datastore.database.impl.sql.SqlTable;

public class MysqlTable extends SqlTable {

	public MysqlTable(MysqlStore store, TableDef def) {
		super(store, def);
	}

}
