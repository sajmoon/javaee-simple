package todo;

import static org.junit.Assert.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.apache.derby.jdbc.EmbeddedDataSource;
import org.apache.naming.java.javaURLContextFactory;
import org.junit.*;

public class ItemTest {

	@BeforeClass
	 public static void setUpClass() throws Exception {
	  System.setProperty(Context.INITIAL_CONTEXT_FACTORY, javaURLContextFactory.class.getName());
	  System.setProperty(Context.URL_PKG_PREFIXES, "org.apache.naming");
	  InitialContext ic = new InitialContext();

	  ic.createSubcontext("java:");
	  ic.createSubcontext("java:comp");
	  ic.createSubcontext("java:comp/env");
	  ic.createSubcontext("java:comp/env/jdbc");

	  EmbeddedDataSource ds = new EmbeddedDataSource();
	  ds.setDatabaseName("tutorialDB");
	  // tell Derby to create the database if it does not already exist
	  ds.setCreateDatabase("create");

	  ic.bind("java:comp/env/jdbc/tutorialDS", ds);
	 }

	@AfterClass
	 public static void tearDownClass() throws Exception {

	  InitialContext ic = new InitialContext();

	  ic.unbind("java:comp/env/jdbc/tutorialDS");
	 }

	@Test
	public void test() {
		EntityManager em = Persistence.createEntityManagerFactory("todos").createEntityManager();
		em.getTransaction().begin();
		
		Item item = new Item("buy milk");
		em.persist(item);
		em.getTransaction().commit();
		System.out.println("item=" + item.getTitle() + " id: " + item.getId());
		assertEquals(item.getTitle(), "buy milk");
		
		Item found = em.find(Item.class, item.getId());
		
		assertEquals(item.getTitle(), found.getTitle());
	}
	

}
