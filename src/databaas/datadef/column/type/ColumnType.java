package databaas.datadef.column.type;

public interface ColumnType {

	public Object toType();

	@SuppressWarnings("unchecked")
	public default <T> T get() {
		return (T) ((toType()));
	}

	public String getQueryName();

}
