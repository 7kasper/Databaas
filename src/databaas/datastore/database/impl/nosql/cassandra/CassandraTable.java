package databaas.datastore.database.impl.nosql.cassandra;

import databaas.datastore.database.impl.nosql.NosqlTable;
import databaas.datatable.TableDef;

public class CassandraTable extends NosqlTable {

	public CassandraTable(CassandraStore store, TableDef def) {
		super(store, def);
	}

}
