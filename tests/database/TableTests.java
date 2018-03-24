package database;

import databaas.datatable.TableDef;
import databaas.datatable.TableDefBuilder;
import junit.framework.TestCase;

public class TableTests extends TestCase {
	
	TableDef meepTableDef = new TableDefBuilder()
	.setName("MEEP")
	.addColumn("Name", String.class)
	.addColumn("Age", Integer.class)
	.build();
	
	public void testTableDefinition() {
		assertTrue(meepTableDef != null);
		assertTrue(meepTableDef.getColumns().length == 2);
		assertTrue(meepTableDef.getColumn("Age").getValue(4) instanceof Integer);
	}
	
	public void testSqliteTable() {
		
	}
	
}
