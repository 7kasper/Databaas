package databaas.datastore.database.impl.nosql.mongodb;

import databaas.datastore.database.impl.nosql.NosqlTable;
import databaas.datatable.TableDef;

public class MongoTable extends NosqlTable {

	public MongoTable(MongoStore store, TableDef def) {
		super(store, def);
	}

}
