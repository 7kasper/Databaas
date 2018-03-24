package databaas.datatable.column.type;

import java.util.function.Function;

public interface TypeDef {
	
	public <T> void addTypeDef(Class<T> type, Class<? extends ColumnType> columnType, Function<T, ColumnType> columnTypeSupplier);
	
	public Class<? extends ColumnType> typeToColumnType(Class<?> type);
	
	public Class<?> columnTypeToType(Class<? extends ColumnType> columnType);
	
	public ColumnType toColumnObject(Object obj);
	
	public Object fromColumnObject(ColumnType columnObj);
	
}
