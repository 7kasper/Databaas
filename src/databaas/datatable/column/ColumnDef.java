package databaas.datatable.column;

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
	
	public Class<?> getColumnType() {
		return clazz;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getValue(Object val) {
		return (T) ((clazz.cast(val)));
	}
	
}
