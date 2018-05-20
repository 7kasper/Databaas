package databaas.datatable.column.type.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import databaas.datatable.column.type.ColumnType;
import databaas.datatable.column.type.TypeDefs;

public abstract class DatabaseTypeDefs implements TypeDefs {

	protected DatabaseTypeDefs() { }

	protected final Map<Class<?>, Class<? extends ColumnType>> typeMap = new HashMap<>();
	protected final Map<Class<? extends ColumnType>, Class<?>> columnTypeMap = new HashMap<>();
	protected final Map<Class<?>, Function<Object, ColumnType>> typeSupplier = new HashMap<>();

	public abstract void init();

	public abstract Function<Object, ColumnType> getTypeSupplier(Class<?> type);

	@Override
	@SuppressWarnings("unchecked")
	public <T> void addTypeDef(Class<T> type, Class<? extends ColumnType> columnType, Function<T, ColumnType> columnTypeSupplier) {
		typeMap.put(type, columnType);
		columnTypeMap.put(columnType, type);
		typeSupplier.put(type, (Function<Object, ColumnType>) columnTypeSupplier);
	}

	@Override
	public Class<? extends ColumnType> typeToColumnType(Class<?> type) {
		return typeMap.getOrDefault(type, typeMap.get(Object.class));
	}

	@Override
	public Class<?> columnTypeToType(Class<? extends ColumnType> columnType) {
		return columnTypeMap.get(columnType);
	}

	@Override
	public ColumnType toColumnObject(Object obj) {
		return getTypeSupplier(obj.getClass()).apply(obj);
	}

	@Override
	public Object fromColumnObject(ColumnType columnObj) {
		return columnObj.toType();
	}

}
