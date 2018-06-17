package databaas.datastore;

import databaas.datatable.TableDef;

public interface Table {

	public TableDef getDefinition();

	public DataStore getStore();

}
