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

	public boolean validate();

	public boolean createTable(TableDef table);
	
	//TODO: Continue :F
	//public void createTable
	
}
