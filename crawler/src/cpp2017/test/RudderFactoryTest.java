package cpp2017.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cpp2017.rudder.RudderFactory;

/**
 * @author david
 * Tests unitaires du RudderFactory
 * 
 */

public class RudderFactoryTest {

	/**
	 * @throws Exception
	 * Est appelé avant tous les tests
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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

	/**
	 * Tests pour la création de Rudders, si le Rudder créé par la factory est le bon.
	 */
	@Test
	public final void testCreationRudder() {
		RudderFactory rudderFactory= new RudderFactory();
		
		assertEquals("CreationRudder1","Rudder1",rudderFactory.getRudder(RudderFactory.TYPE_RUDDER1).toString());
	/*
	 * Equivalent à:
	 * if(!rudderFactory.getRudder(RudderFactory.TYPE_RUDDER1).toString().equals("Rudder1"))
		fail("CreationRudder1");
	**/
	
	}
}
