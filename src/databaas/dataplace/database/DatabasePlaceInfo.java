package databaas.dataplace.database;

import java.util.List;

import databaas.dataplace.NoPlaceException;
import databaas.dataplace.PlaceInfo;
import databaas.dataplace.database.impl.DatabaseConnectPlace;

public interface DatabasePlaceInfo extends PlaceInfo {
	
	/***
	 * Adds a place to connect to the DB.
	 * @param connectPlace
	 */
	public void addConnectPlace(DatabaseConnectPlace connectPlace);

	/***
	 * @return all free to connect places.
	 */
	public List<DatabaseConnectPlace> getFreeConnectPlaces();

	/***
	 * @return all buzy (inuse) connect places.
	 */
	public List<DatabaseConnectPlace> getBuzyConnectPlaces();
	
	/***
	 * Gets a random free connect place and marks it buzy.
	 * @return the free place.
	 * @throws NoPlaceException - when no place is actually free.
	 */
	public DatabaseConnectPlace getFreeConnectPlace() throws NoPlaceException;

	/***
	 * Gets a random connect place to read from.
	 * NOTE: This may and will return non-free connect places and should only be used to read.
	 * @return the connect place.
	 * @throws NoPlaceException - when there's no place at all.
	 */
	public DatabaseConnectPlace getAConnectPlace() throws NoPlaceException;

	/***
	 * Marks a ConnectPlace as free to use.
	 * @param place
	 */
	public void markFree(DatabaseConnectPlace place);

	/***
	 * Marks a ConnectPlace as buzy (inuse).
	 * @param place
	 */
	public void markBuzy(DatabaseConnectPlace place);

}
