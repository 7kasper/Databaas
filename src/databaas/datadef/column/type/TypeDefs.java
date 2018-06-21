package databaas.datadef.column.type;

import java.util.function.Function;

import databaas.datadef.column.option.ColumnOption;

public interface TypeDefs {

	public void init();

	public <T> void addTypeDef(Class<T> type, Class<? extends ColumnType> columnType, Function<T, ColumnType> columnTypeSupplier);

	public <T extends ColumnOption> void addOptionDef(Class<T> type, Function<T, ColumnOptionType> optionTypeSupplier);

	public Class<? extends ColumnType> typeToColumnType(Class<?> type);

	public Class<?> columnTypeToType(Class<? extends ColumnType> columnType);

	public ColumnType toColumnObject(Object obj);

	public Object fromColumnObject(ColumnType columnObj);

	public ColumnOptionType toColumnOptionType(ColumnOption option);

	public ColumnOption fromColumnOptionType(ColumnOptionType optionType);

}
