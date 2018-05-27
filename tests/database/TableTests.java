package database;

import databaas.Databaas;
import databaas.datastore.DataStore;
import databaas.datatable.TableDef;
import databaas.datatable.TableDefBuilder;
import databaas.datatable.column.option.NotNullOption;
import databaas.datatable.column.option.PrimaryKeyOption;
import databaas.datatable.column.type.impl.nosql.cassandra.types.CassandraString;
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

	DataStore store = Databaas.openStore("SQLITE", new DatabaseDataPlace("Minecraft", new DatabaseConnectPlace("D:/Kasper/BeastnodeServer/Test/")));
	
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
	
	public void testSqliteTable() {
		assertTrue(store.validate());
		assertTrue(store.createTable(meepTableDef));
	}
	
}
