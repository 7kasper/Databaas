package databaas.datatable;

import java.util.ArrayList;
import java.util.List;

import databaas.datatable.column.ColumnDef;

public class TableDefBuilder {
	
	public String tableName;
	public List<ColumnDef> columns = new ArrayList<>();
	
	public TableDefBuilder setName(String tableName) {
		this.tableName = tableName;
		return this;
	}
	
	public TableDefBuilder addColumn(String name, Class<?> clazz) {
		columns.add(new ColumnDef(name, clazz));
		return this;
	}
	
	public TableDef build() {
		return new TableDef(tableName, 
				columns.toArray(new ColumnDef[columns.size()]));
	}
	
}
