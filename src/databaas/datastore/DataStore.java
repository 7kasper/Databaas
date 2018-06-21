package databaas.datastore;

import databaas.dataplace.PlaceException;
import databaas.datadef.column.type.TypeDefs;
import databaas.datadef.table.TableDef;
import databaas.dataplace.NoPlaceException;
import databaas.dataplace.PlaceInfo;

public interface DataStore {
	
	/**
	 * @return the type belonging to this DataStore.
	 */
	public String getType();
	
	/**
	 * @return the PlaceInfo belonging to this DataStore.
	 */
	public PlaceInfo getConnect();
	
	/**
	 * @return the TypeDefs belonging to this DataStore.
	 */
	public TypeDefs getTypeDefs();

	/**
	 * Engages the datastore.
	 * @throws NoPlaceException - When there is no place to connect to.
	 * @throws PlaceException - When the store doesn't want to work with us.
	 */
	public void engage() throws NoPlaceException, PlaceException;

	/**
	 * Sets up the datastore and checks if it is valid.
	 * @return true if the store is valid and in theory, ready to go.
	 */
	public boolean setup();

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
