package databaas.datatable;

import databaas.datatable.column.ColumnDef;

public class TableDef {
	
	private final String tableName;
	private final ColumnDef[] columns;
	
	public TableDef(String tableName, ColumnDef... columns) {
		this.tableName = tableName;
		this.columns = columns;
	}
	
	public final String getTableName() {
		return tableName;
	}
	
	public final ColumnDef[] getColumns() {
		return columns;
	}
	
	public final ColumnDef getColumn(int i) {
		return columns[i];
	}
	
	public final ColumnDef getColumn(String name) {
		for (ColumnDef column : columns) {
			if (column.getColumnName().equals(name)) {
				return column;
			}
		}
		return null;
	}
	
}
