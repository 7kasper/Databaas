package databaas.datastore.database.impl.nosql;

import databaas.datadef.table.TableDef;
import databaas.datastore.database.impl.DatabaseTable;

public abstract class NosqlTable extends DatabaseTable {

	public NosqlTable(NosqlStore store, TableDef def) {
		super(store, def);
	}

}
