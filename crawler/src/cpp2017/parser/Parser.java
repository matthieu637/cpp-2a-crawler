package cpp2017.parser;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;


import cpp2017.rudder.Rudder;


/**
 * @author MathieuDEVALLE, David 
 * Classe permettant de parser une page web à partir
 * d'une url
 * Thread différent des autres car les requêtes
 *         réseaux sont plus longues à exécuter.
 */
public abstract class Parser extends Thread {

	public abstract LinkedList<String> getLinks() throws IOException;

	public abstract HashMap<String, Vector<String>> getInfos() throws IOException;
	
	public abstract void registerRudder(Rudder r);
	
	public abstract void run();

	protected abstract void changeCurrentLink(String link);
}
