package databaas.datadef.column.type.impl.sql.sqlite.option;

import databaas.datadef.column.option.ColumnOption;
import databaas.datadef.column.option.PrimaryKeyOption;
import databaas.datadef.column.type.impl.sql.sqlite.SqliteColumnOptionType;

public class SqlitePrimaryKeyOption extends SqliteColumnOptionType {

	private PrimaryKeyOption option;

	public SqlitePrimaryKeyOption() { }

	public SqlitePrimaryKeyOption(PrimaryKeyOption option) {
		this.option = option;
	}

	@Override
	public ColumnOption getOption() {
		return option;
	}

	@Override
	public String getQueryString() {
		return "PRIMARY KEY";
	}

}
