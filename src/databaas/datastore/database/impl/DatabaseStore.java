package databaas.datastore.database.impl;

import databaas.dataplace.PlaceInfo;
import databaas.dataplace.database.DatabasePlaceInfo;
import databaas.datastore.DataStore;

public abstract class DatabaseStore implements DataStore {
	
	private final DatabasePlaceInfo place;
	
	public DatabaseStore(PlaceInfo place) {
		this.place = (DatabasePlaceInfo) place;
	}
	
	@Override
	public DatabasePlaceInfo getConnect() {
		return place;
	}
	
}
