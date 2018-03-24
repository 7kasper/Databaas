package databaas.datastore;

import databaas.dataplace.PlaceInfo;
import databaas.datatable.column.type.TypeDef;

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
	 * @return the TypeDef belonging to this DataStore.
	 */
	public TypeDef getTypeDef();
	
	//TODO: Continue :F
	//public void createTable
	
}
