package databaas.datastore.database.impl.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import databaas.DatabaasLogger;
import databaas.dataplace.NoPlaceException;
import databaas.dataplace.PlaceInfo;
import databaas.dataplace.database.impl.DatabaseConnectPlace;
import databaas.datastore.Table;
import databaas.datastore.database.impl.DatabaseStore;
import databaas.datatable.TableDef;
import databaas.datatable.column.ColumnDef;
import databaas.datatable.column.type.ColumnOptionType;

public abstract class SqlStore extends DatabaseStore {

	public SqlStore(PlaceInfo place) {
		super(place);
	}

	public abstract String getJDBCurl(DatabaseConnectPlace dcp);

	public boolean exec(String sql) {
		try {
			DatabaseConnectPlace dcp = getConnect().getFreeConnectPlace();
			Connection con = null;
			StringBuilder jdbcUrl = new StringBuilder();
			jdbcUrl.append("jdbc:").append(getType()).append(":")
				   .append(getJDBCurl(dcp));
			try {
				con = DriverManager.getConnection(jdbcUrl.toString());
				if (sql != null) {
					con.createStatement().execute(sql);
				}
				return true;
			} finally {
				if (con != null) {
					con.close();
				}
				getConnect().markFree(dcp);
			}
		} catch (SQLException | NoPlaceException e) {
			DatabaasLogger.log(e, "Error while executing table!");
			return false;
		}

	}

	public Table createTable(TableDef table) {
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
			exec(query.toString());
			return getTable(table);
		} catch (InstantiationException | IllegalAccessException e) {
			DatabaasLogger.log(e, "Error while creating table!");
		}
		return null;
	}

}
