package cpp2017.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cpp2017.rudder.RudderFactory;

public class RudderFactoryTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreationRudder() {
		RudderFactory rudderFactory= new RudderFactory();
		
		assertEquals("CreationRudder1","Rudder1",rudderFactory.getRudder(RudderFactory.TYPE_RUDDER1).toString());
	/*
	 * Equivalent à:
	 * if(!rudderFactory.getRudder(RudderFactory.TYPE_RUDDER1).toString().equals("Rudder1"))
		fail("CreationRudder1");
	**/
	
	}
}
