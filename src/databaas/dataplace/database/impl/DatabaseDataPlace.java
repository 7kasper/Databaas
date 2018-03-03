package databaas.dataplace.database.impl;

import databaas.dataplace.database.DatabasePlaceInfo;

public class DatabaseDataPlace implements DatabasePlaceInfo {

	private final String placename;
	
	public DatabaseDataPlace(String placename) {
		this.placename = placename;
	}
	
	@Override
	public String getPlaceName() {
		return placename;
	}

}
