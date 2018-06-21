package databaas.datastore;

import databaas.datadef.table.TableDef;

public interface Table {

	public TableDef getDefinition();

	public DataStore getStore();

}
