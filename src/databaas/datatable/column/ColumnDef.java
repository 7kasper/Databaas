package databaas.datatable.column;

import databaas.datatable.column.type.ColumnType;
import databaas.datatable.column.type.TypeDefs;

public class ColumnDef {
	
	private String columnName;
	private Class<?> clazz;
	
	public ColumnDef(String columnName, Class<?> clazz) {
		this.columnName = columnName;
		this.clazz = clazz;
	}
	
	public String getColumnName() {
		return columnName;
	}
	
	public Class<?> getType() {
		return clazz;
	}

	public Class<? extends ColumnType> getColumnType(TypeDefs defs) {
		return defs.typeToColumnType(getType());
	}

	public String getColumnTypeName(TypeDefs defs) throws InstantiationException, IllegalAccessException {
		return getColumnType(defs).newInstance().getQueryName();
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getValue(Object val) {
		return (T) ((clazz.cast(val)));
	}
	
}
