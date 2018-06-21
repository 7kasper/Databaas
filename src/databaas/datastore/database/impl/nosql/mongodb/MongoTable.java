package databaas.datastore.database.impl.nosql.mongodb;

import databaas.datadef.table.TableDef;
import databaas.datastore.database.impl.nosql.NosqlTable;

public class MongoTable extends NosqlTable {

	public MongoTable(MongoStore store, TableDef def) {
		super(store, def);
	}

}
