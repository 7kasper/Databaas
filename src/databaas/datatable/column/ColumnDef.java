package databaas.datatable.column;

import databaas.datatable.column.option.ColumnOption;
import databaas.datatable.column.type.ColumnOptionType;
import databaas.datatable.column.type.ColumnType;
import databaas.datatable.column.type.TypeDefs;

public class ColumnDef {
	
	private String columnName;
	private Class<?> clazz;
	private ColumnOption[] options;
	
	public ColumnDef(String columnName, Class<?> clazz, ColumnOption... options) {
		this.columnName = columnName;
		this.clazz = clazz;
		this.options = options;
	}
	
	public String getColumnName() {
		return columnName;
	}
	
	public Class<?> getType() {
		return clazz;
	}

	public ColumnOption[] getOptions() {
		return options;
	}

	public Class<? extends ColumnType> getColumnType(TypeDefs defs) {
		return defs.typeToColumnType(getType());
	}

	public ColumnOptionType[] getOptionTypes(TypeDefs defs) {
		ColumnOptionType[] optionTypes = new ColumnOptionType[options.length];
		for (int i = 0; i < options.length; i++) {
			optionTypes[i] = defs.toColumnOptionType(options[i]);
		}
		return optionTypes;
	}

	public String getColumnTypeName(TypeDefs defs) throws InstantiationException, IllegalAccessException {
		return getColumnType(defs).newInstance().getQueryName();
	}
	
}
