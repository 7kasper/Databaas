package databaas.datastore;

import databaas.dataplace.PlaceInfo;
import databaas.datatable.TableDef;
import databaas.datatable.column.type.TypeDefs;

public interface DataStore {
	
	/**
	 * @return the type belonging to this DataStore.
	 */
	public String getType();
	
	/**
	 * @return the PlaceInfo belonging to this DataStore.
	 */
	public PlaceInfo getConnect();
	
	/*
	 * @return the TypeDefs belonging to this DataStore.
	 */
	public TypeDefs getTypeDefs();

	/**
	 * Checks if the datastore is valid.
	 * @return true if the store is valid and in theory, ready to go.
	 */
	public boolean validate();

	/**
	 * Creates a table following the specified {@link TableDef}.
	 * @param def - the definition of the table.
	 * @return the created {@link Table} implementation to interact with.
	 * null, if the database doesn't report something went wrong.
	 */
	public Table createTable(TableDef def);

	/**
	 * Gets accesspoint to a table in the store.
	 * @param def - the definition of the table.
	 * @return a new {@link Table} implementation to interact with.
	 */
	public Table getTable(TableDef def);

}
