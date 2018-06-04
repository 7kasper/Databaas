package databaas.datastore.database.impl.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

import databaas.DatabaasLogger;
import databaas.dataplace.NoPlaceException;
import databaas.dataplace.PlaceInfo;
import databaas.dataplace.database.impl.DatabaseConnectPlace;
import databaas.datastore.database.impl.DatabaseStore;
import databaas.datatable.TableDef;
import databaas.datatable.column.ColumnDef;
import databaas.datatable.column.type.ColumnOptionType;

public abstract class SqlStore extends DatabaseStore {

	public SqlStore(PlaceInfo place) {
		super(place);
	}

	public abstract String getJDBCurl(DatabaseConnectPlace dcp);

	public void exec(Consumer<Connection> func) throws NoPlaceException, SQLException {
		DatabaseConnectPlace dcp = getConnect().getFreeConnectPlace();
		Connection con = null;
		StringBuilder jdbcUrl = new StringBuilder();
		jdbcUrl.append("jdbc:").append(getType()).append(":")
			   .append(getJDBCurl(dcp));
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

	public boolean createTable(TableDef table) {
		AtomicBoolean successful = new AtomicBoolean();
		try {
			StringBuilder query = new StringBuilder();
			query.append("CREATE TABLE IF NOT EXISTS ")
				 .append(table.getTableName())
				 .append(" (\n");
			for (int i = 0; i < table.getColumnLength() ; i++) {
				ColumnDef column = table.getColumn(i);
				query.append(" ")
					 .append(column.getColumnName())
					 .append(" ")
					 .append(column.getColumnTypeName(getTypeDefs()));
				for (ColumnOptionType option : column.getOptionTypes(getTypeDefs())) {
					query.append(" ");
					query.append(option.getQueryString());
				}
				if (i < (table.getColumnLength() - 1)) {
					query.append(", \n");
				}
			}
			query.append(");");
			
			exec((con) -> {
				try {
					con.createStatement().execute(query.toString());
					successful.set(true);
				} catch (SQLException e) { DatabaasLogger.log(e, "Error while creating table!"); }
			});
		} catch (NoPlaceException | SQLException | InstantiationException | IllegalAccessException e) {
			DatabaasLogger.log(e, "Error while creating table!");
		}
		return successful.get();
	}

}
