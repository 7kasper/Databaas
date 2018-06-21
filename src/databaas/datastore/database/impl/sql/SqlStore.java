package databaas.datastore.database.impl.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import databaas.DatabaasLogger;
import databaas.datadef.column.ColumnDef;
import databaas.datadef.column.type.ColumnOptionType;
import databaas.datadef.table.TableDef;
import databaas.dataplace.PlaceException;
import databaas.dataplace.NoPlaceException;
import databaas.dataplace.PlaceInfo;
import databaas.dataplace.database.impl.DatabaseConnectPlace;
import databaas.datastore.Table;
import databaas.datastore.database.impl.DatabaseStore;

public abstract class SqlStore extends DatabaseStore {

	DatabaseConnectPlace dcp = null;
	Connection con = null;

	public SqlStore(PlaceInfo place) {
		super(place);
	}

	public abstract String getJDBCurl(DatabaseConnectPlace dcp);

	public void engage() throws NoPlaceException, PlaceException {
		StringBuilder jdbcUrl = new StringBuilder();
		jdbcUrl.append("jdbc:").append(getType()).append(":")
			   .append(getJDBCurl(dcp));
		dcp = getConnect().getFreeConnectPlace();
		try {
			con = DriverManager.getConnection(jdbcUrl.toString());
		} catch (SQLException e) {
			throw new PlaceException(e.getMessage(), e.getCause());
		}
	}

	public void disengage() throws PlaceException {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				throw new PlaceException(e.getMessage(), e.getCause());
			}
		}
		if (dcp != null) {
			getConnect().markFree(dcp);
		}
	}

	public boolean exec(String sql) throws NoPlaceException, SQLException {
		DatabaseConnectPlace dcp = getConnect().getFreeConnectPlace();
		Connection con = null;
		StringBuilder jdbcUrl = new StringBuilder();
		jdbcUrl.append("jdbc:").append(getType()).append(":")
			   .append(getJDBCurl(dcp));
		try {
			con = DriverManager.getConnection(jdbcUrl.toString());
			if (sql != null) {
				return con.createStatement().execute(sql);
			}
		} finally {
			if (con != null) {
				con.close();
			}
			getConnect().markFree(dcp);
		}
		return false;
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
		} catch (InstantiationException | IllegalAccessException | NoPlaceException | SQLException e) {
			DatabaasLogger.log(e, "Error while creating table!");
		}
		return null;
	}

}
