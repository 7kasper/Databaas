package databaas.datatable.column.type.impl.sql.sqlite.option;

import databaas.datatable.column.option.ColumnOption;
import databaas.datatable.column.option.NotNullOption;
import databaas.datatable.column.type.impl.sql.sqlite.SqliteColumnOptionType;

public class SqliteNotNullOption extends SqliteColumnOptionType {

	private NotNullOption option;

	public SqliteNotNullOption() { };

	public SqliteNotNullOption(NotNullOption option) {
		this.option = option;
	}

	@Override
	public ColumnOption getOption() {
		return option;
	}

	@Override
	public String getQueryString() {
		return "NOT NULL";
	}

}
