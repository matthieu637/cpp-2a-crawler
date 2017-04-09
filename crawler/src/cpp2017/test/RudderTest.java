package cpp2017.test;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cpp2017.parser.LinkQueue;
import cpp2017.rudder.Rudder;
import cpp2017.rudder.RudderFactory;

/**
 * @author david
 * Tests unitaires des Rudders
 * 
 */

public class RudderTest {

	static RudderFactory rudderFactory;
	static Rudder rudder1;
	
	
	/**
	 * @throws Exception
	 * Est appelé avant tous les tests
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		rudderFactory= new RudderFactory();
		rudder1=rudderFactory.getRudder(RudderFactory.TYPE_RUDDER1);
	}

	/**
	 * @throws Exception
	 * Est appelé après tous les tests
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws Exception
	 * Est appelé avant chaque test
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws Exception
	 * Est appelé après chaque test
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEnvoieLien() {
		rudder1.addLink(Arrays.asList("http://stackoverflow.com/","https://ent.univ-lorraine.fr/"));
		assertEquals("Bon nombre d'éléments",LinkQueue.getInstance().size(),2);
		assertEquals("Envoie Rudder1 to LinkQueue, 1er lien","PriorityLink [url=http://stackoverflow.com/]",LinkQueue.getInstance().getLink().toString());
		assertEquals("Envoie Rudder1 to LinkQueue, 2eme lien","PriorityLink [url=https://ent.univ-lorraine.fr/]",LinkQueue.getInstance().getLink().toString());
	}

}
