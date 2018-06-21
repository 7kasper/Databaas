package database;

import databaas.Databaas;
import databaas.datadef.column.option.NotNullOption;
import databaas.datadef.column.option.PrimaryKeyOption;
import databaas.datadef.column.type.impl.nosql.cassandra.types.CassandraString;
import databaas.datadef.table.TableDef;
import databaas.datadef.table.TableDefBuilder;
import databaas.datastore.DataStore;
import databaas.datastore.Table;
import database.util.TestUtils;
import databaas.dataplace.database.impl.DatabaseConnectPlace;
import databaas.dataplace.database.impl.DatabaseDataPlace;
import junit.framework.TestCase;

public class TableTests extends TestCase {
	
	TableDef meepTableDef = new TableDefBuilder()
	.setName("MEEP")
	.addColumn("Id", Integer.class, new PrimaryKeyOption(), new NotNullOption())
	.addColumn("Name", String.class)
	.addColumn("Age", Integer.class)
	.build();
	
	public void testTableDefinition() {
		assertTrue(meepTableDef != null);
		assertTrue(meepTableDef.getColumns().length == 3);
		CassandraString cs = new CassandraString("FFF");
		String meep = cs.get();
		assertTrue(!meep.isEmpty());
		System.out.println(meep);
		meep = meep.replace('F', 'Z');
		assertTrue(meep.equals("ZZZ"));

	}

	DataStore sqlitestore = Databaas.openStore("SQLITE", new DatabaseDataPlace("TestDB", new DatabaseConnectPlace(TestUtils.getSqliteTestDir().getPath())));
	
	public void testSqliteTable() {
		assertTrue(sqlitestore.setup());
		Table meepTable = sqlitestore.createTable(meepTableDef);
		assertNotNull(meepTable);
		//meepTable.get();
	}

}
