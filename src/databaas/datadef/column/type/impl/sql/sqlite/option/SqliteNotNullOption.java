package databaas.datadef.column.type.impl.sql.sqlite.option;

import databaas.datadef.column.option.ColumnOption;
import databaas.datadef.column.option.NotNullOption;
import databaas.datadef.column.type.impl.sql.sqlite.SqliteColumnOptionType;

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
