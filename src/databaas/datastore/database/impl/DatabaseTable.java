package databaas.datastore.database.impl;

import databaas.datastore.DataStore;
import databaas.datastore.Table;
import databaas.datatable.TableDef;

public abstract class DatabaseTable implements Table {

	protected DataStore store;
	protected TableDef def;

	public DatabaseTable(DataStore store, TableDef def) {
		this.store = store;
		this.def = def;
	}

	@Override
	public TableDef getDefinition() {
		return def;
	}

	@Override
	public DataStore getStore() {
		return store;
	}

}
