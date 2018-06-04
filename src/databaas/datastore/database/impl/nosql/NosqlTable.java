package databaas.datastore.database.impl.nosql;

import databaas.datastore.database.impl.DatabaseTable;
import databaas.datatable.TableDef;

public abstract class NosqlTable extends DatabaseTable {

	public NosqlTable(NosqlStore store, TableDef def) {
		super(store, def);
	}

}
