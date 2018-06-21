package databaas.datastore.database.impl.nosql.cassandra;

import databaas.datadef.table.TableDef;
import databaas.datastore.database.impl.nosql.NosqlTable;

public class CassandraTable extends NosqlTable {

	public CassandraTable(CassandraStore store, TableDef def) {
		super(store, def);
	}

}
