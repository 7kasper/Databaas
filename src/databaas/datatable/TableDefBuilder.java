package databaas.datatable;

import java.util.ArrayList;
import java.util.List;

import databaas.datatable.column.ColumnDef;
import databaas.datatable.column.option.ColumnOption;

public class TableDefBuilder {
	
	public String tableName;
	public List<ColumnDef> columns = new ArrayList<>();
	
	public TableDefBuilder setName(String tableName) {
		this.tableName = tableName;
		return this;
	}
	
	public TableDefBuilder addColumn(String name, Class<?> clazz, ColumnOption... options) {
		columns.add(new ColumnDef(name, clazz, options));
		return this;
	}
	
	public TableDef build() {
		return new TableDef(tableName, 
				columns.toArray(new ColumnDef[columns.size()]));
	}
	
}
