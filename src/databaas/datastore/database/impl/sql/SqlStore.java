package databaas.datastore.database.impl.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.function.Consumer;

import databaas.DatabaasLogger;
import databaas.dataplace.NoPlaceException;
import databaas.dataplace.PlaceInfo;
import databaas.dataplace.database.impl.DatabaseConnectPlace;
import databaas.datastore.database.impl.DatabaseStore;
import databaas.datatable.TableDef;
import databaas.datatable.column.ColumnDef;
import databaas.datatable.column.type.ColumnType;

public abstract class SqlStore extends DatabaseStore {

	public SqlStore(PlaceInfo place) {
		super(place);
	}

	public void exec(Consumer<Connection> func) throws NoPlaceException, SQLException {
		DatabaseConnectPlace dcp = getConnect().getAConnectPlace();
		Connection con = null;
		StringBuilder jdbcUrl = new StringBuilder();
		jdbcUrl.append("jdbc:").append(getType()).append(":");
		if (getType() == "SQLITE") {
			jdbcUrl.append(dcp.getUrl()).append(getConnect().getPlaceName()).append(".db");
		} else {
			//TODO MYSQL jdbc code
		}
		try {
			con = DriverManager.getConnection(jdbcUrl.toString());
			func.accept(con);
		} finally {
			if (con != null) {
				con.close();
			}
			getConnect().markFree(dcp);
		}
	}

	public void createTable(TableDef table) throws NoPlaceException, SQLException, InstantiationException, IllegalAccessException {
		StringBuilder query = new StringBuilder();
		query.append("CREATE TABLE IF NOT EXISTS ")
			 .append(table.getTableName())
			 .append("(\n");
		for (ColumnDef column : table.getColumns()) {
			query.append(" ")
				 .append(column.getColumnName())
				 .append(" ")
				 .append(column.getColumnTypeName(getTypeDefs()));
		}
			
		exec((con) -> {
			try {
				con.createStatement().execute(query.toString());
			} catch (SQLException e) { DatabaasLogger.log(e, "Error while creating table!"); }
		});
	}

}
