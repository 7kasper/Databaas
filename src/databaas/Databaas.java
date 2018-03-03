package databaas;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import databaas.dataplace.PlaceInfo;
import databaas.datastore.DataStore;
import databaas.datastore.database.impl.nosql.cassandra.CassandraStore;
import databaas.datastore.database.impl.nosql.mongodb.MongoStore;
import databaas.datastore.database.impl.sql.mysql.MysqlStore;
import databaas.datastore.database.impl.sql.sqlite.SqliteStore;

public class Databaas {
	
	//Archive for DataStore access.
	private static final Map<String, DataStore> datastores = new ConcurrentHashMap<String, DataStore>();
	
	//Archive for DataStore creation.
	private static final Map<String, Function<PlaceInfo, DataStore>> storeSupplier = new HashMap<>();
	static {
		registerDataStore("SQLITE", 	(info) -> new SqliteStore(info));
		registerDataStore("MYSQL", 		(info) -> new MysqlStore(info));
		registerDataStore("MONGODB", 	(info) -> new CassandraStore(info));
		registerDataStore("CASSANDRA", 	(info) -> new MongoStore(info));
	}
	
	/**
	 * Registers a new DataStore with the supplier used to create a new instance of that store.
	 * @param type
	 * @param supplier
	 */
	public static void registerDataStore(String type, Function<PlaceInfo, DataStore> supplier) {
		storeSupplier.putIfAbsent(type, supplier);
	}
	
	/**
	 * Get the supplier for the creation of a store from type.
	 * @param type
	 * @return the registered supplier.
	 * @throws IllegalArgumentException if supplier for type is not registered.
	 */
	public static Function<PlaceInfo, DataStore> getStoreSupply(String type) throws IllegalArgumentException {
		return storeSupplier.getOrDefault(type, (info) -> {
			throw new IllegalArgumentException("Unknwown store type: " + type);
		});
	}
	
	/**
	 * Gets the 
	 * @param placeName
	 * @return the DataStore belonging to placeName.
	 */
	public static DataStore locateStore(String placename) {
		return datastores.get(placename);
	}
	
	/**
	 * Opens a new DataStore able to access type using {@link PlaceInfo} info.
	 * @param type
	 * @param connect
	 * @return the new DataStore implementation.
	 * @throws IllegalArgumentException if supplier for type is not registered.
	 * @throws ClassCastException if the PlaceInfo is not of correct type for the creation of the store.
	 */
	public static DataStore openStore(String type, PlaceInfo info) throws IllegalArgumentException, ClassCastException {
		DataStore ds = getStoreSupply(type).apply(info);
		datastores.putIfAbsent(info.getPlaceName(), ds);
		return ds;
	}
	
}
