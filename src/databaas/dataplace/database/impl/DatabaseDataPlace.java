package databaas.dataplace.database.impl;

import java.util.ArrayList;
import java.util.List;

import databaas.dataplace.NoPlaceException;
import databaas.dataplace.database.DatabasePlaceInfo;
import databaas.util.java.RandomUtils;

public class DatabaseDataPlace implements DatabasePlaceInfo {

	private final String placename;
	private List<DatabaseConnectPlace> freeConnectPlaces = new ArrayList<>();
	private List<DatabaseConnectPlace> buzyConnectPlaces = new ArrayList<>();

	public DatabaseDataPlace(String placename) {
		this.placename = placename;
	}

	public DatabaseDataPlace(String placename, DatabaseConnectPlace place) {
		this(placename);
		addConnectPlace(place);
	}

	@Override
	public String getPlaceName() {
		return placename;
	}

	@Override
	public void addConnectPlace(DatabaseConnectPlace connectPlace) {
		freeConnectPlaces.add(connectPlace);
	}

	@Override
	public List<DatabaseConnectPlace> getFreeConnectPlaces() {
		return freeConnectPlaces;
	}

	@Override
	public List<DatabaseConnectPlace> getBuzyConnectPlaces() {
		return buzyConnectPlaces;
	}

	@Override
	public DatabaseConnectPlace getAConnectPlace() throws NoPlaceException {
		DatabaseConnectPlace place = RandomUtils.fromList(freeConnectPlaces);
		if (place == null) {
			throw new NoPlaceException("There is no place to connect left!");
		}
		markBuzy(place);
		return place;
	}

	@Override
	public void markFree(DatabaseConnectPlace place) {
		buzyConnectPlaces.remove(place);
		freeConnectPlaces.add(place);
	}

	@Override
	public void markBuzy(DatabaseConnectPlace place) {
		freeConnectPlaces.remove(place);
		buzyConnectPlaces.add(place);
	}

}
