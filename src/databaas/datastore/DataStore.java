package databaas.datastore;

import databaas.dataplace.PlaceInfo;

public interface DataStore {
	
	/**
	 * @return the PlaceInfo belonging to this DataStore.
	 */
	public PlaceInfo getConnect();
	
	/**
	 * @return the type belonging to this DataStore.
	 */
	public String getType();
	
	//TODO: Continue :F
	//public void createTable
	
}
