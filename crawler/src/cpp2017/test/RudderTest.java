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
	static Rudder naiveRudder;
	
	
	/**
	 * @throws Exception
	 * Est appelé avant tous les tests
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		naiveRudder=RudderFactory.getInstance().getRudder(RudderFactory.TYPE_NAIVE_RUDDER);
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
		LinkQueue.getInstance().clearAll();
	}
/*
  // test plus d'actualité car maintenant les liens déjà parsés ne passent pas
	@Test
	public void testPriorityEnvoieLien() {
		//test priority, plus priority est petit, plus le lien est prioritaire
		naiveRudder.addPriorityLink(Arrays.asList(new PriorityLink("http://stackoverflow.com/",0),new PriorityLink("https://ent.univ-lorraine.fr/",1)));
		assertEquals("Bon nombre d'éléments",LinkQueue.getInstance().size(),2);
		assertEquals("Envoie Rudder1 to LinkQueue, 1er lien","PriorityLink [url=http://stackoverflow.com/]",LinkQueue.getInstance().getLink().toString());
		assertEquals("Envoie Rudder1 to LinkQueue, 2eme lien","PriorityLink [url=https://ent.univ-lorraine.fr/]",LinkQueue.getInstance().getLink().toString());
		
		naiveRudder.addPriorityLink(Arrays.asList(new PriorityLink("http://stackoverflow.com/",-1),new PriorityLink("https://ent.univ-lorraine.fr/",0)));
		assertEquals("Bon nombre d'éléments",LinkQueue.getInstance().size(),2);
		assertEquals("Envoie Rudder1 to LinkQueue, 1er lien","PriorityLink [url=http://stackoverflow.com/]",LinkQueue.getInstance().getLink().toString());
		assertEquals("Envoie Rudder1 to LinkQueue, 2eme lien","PriorityLink [url=https://ent.univ-lorraine.fr/]",LinkQueue.getInstance().getLink().toString());
		
		naiveRudder.addPriorityLink(Arrays.asList(new PriorityLink("http://stackoverflow.com/",3000),new PriorityLink("https://ent.univ-lorraine.fr/",-145)));
		assertEquals("Bon nombre d'éléments",LinkQueue.getInstance().size(),2);
		assertEquals("Envoie Rudder1 to LinkQueue, 1er lien","PriorityLink [url=https://ent.univ-lorraine.fr/]",LinkQueue.getInstance().getLink().toString());
		assertEquals("Envoie Rudder1 to LinkQueue, 2eme lien","PriorityLink [url=http://stackoverflow.com/]",LinkQueue.getInstance().getLink().toString());
		
		naiveRudder.addPriorityLink(Arrays.asList(new PriorityLink("http://stackoverflow.com/",3012),new PriorityLink("https://ent.univ-lorraine.fr/",50)));
		assertEquals("Bon nombre d'éléments",LinkQueue.getInstance().size(),2);
		assertEquals("Envoie Rudder1 to LinkQueue, 1er lien","PriorityLink [url=https://ent.univ-lorraine.fr/]",LinkQueue.getInstance().getLink().toString());
		assertEquals("Envoie Rudder1 to LinkQueue, 2eme lien","PriorityLink [url=http://stackoverflow.com/]",LinkQueue.getInstance().getLink().toString());
		
		naiveRudder.addPriorityLink(Arrays.asList(new PriorityLink("http://stackoverflow.com/",-8),new PriorityLink("https://ent.univ-lorraine.fr/",-9)));
		assertEquals("Bon nombre d'éléments",LinkQueue.getInstance().size(),2);
		assertEquals("Envoie Rudder1 to LinkQueue, 1er lien","PriorityLink [url=https://ent.univ-lorraine.fr/]",LinkQueue.getInstance().getLink().toString());
		assertEquals("Envoie Rudder1 to LinkQueue, 2eme lien","PriorityLink [url=http://stackoverflow.com/]",LinkQueue.getInstance().getLink().toString());
	}*/
	/**
	 * Test si le naiveRudder renvoie les liens dans l'ordre de la réception
	 * Pour l'instant il n'y a pas de suppression de doublons.
	 */
	@Test
	public void testNaiveRudder(){
		naiveRudder.addLink(Arrays.asList("http://stackoverflow.com/","https://ent.univ-lorraine.fr/","http://arche.univ-lorraine.fr/course/","https://github.com/"));
		
		assertEquals("Bon nombre d'éléments",LinkQueue.getInstance().size(),4);
		
		assertEquals("Premier élément","http://stackoverflow.com/",LinkQueue.getInstance().getLink().getUrl());
		assertEquals("Deuxième élément","https://ent.univ-lorraine.fr/",LinkQueue.getInstance().getLink().getUrl());
		assertEquals("Troisième élément en ToString","PriorityLink [url=http://arche.univ-lorraine.fr/course/]",LinkQueue.getInstance().getLink().toString());
		assertEquals("Dernier élément","https://github.com/",LinkQueue.getInstance().getLink().getUrl());
		
		assertEquals("Bon nombre d'éléments",LinkQueue.getInstance().size(),0);
	}
	
	@Test 
	public void testSuppressionDoublon(){
		naiveRudder.addLink(Arrays.asList("http://stackoverflow.com/","http://stackoverflow.com/"));
		assertEquals("Problème suppression doublon",LinkQueue.getInstance().size(),1);
	}

}
