package cpp2017;

import java.io.IOException;
import cpp2017.parser.Parser;
import cpp2017.rudder.Rudder;
import cpp2017.rudder.RudderFactory;


public class Principale {

	public static void main(String[] args) throws IOException {
		RudderFactory testRudderFactory = new RudderFactory();
		//Creation d'un Rudder Naif, limite de nombres de liens à parser égal à 50
		Rudder testRudder1 = testRudderFactory.getRudder(RudderFactory.TYPE_NAIVE_RUDDER,50);
		System.out.println(testRudder1);
		Parser testParser = new Parser("http://arche.univ-lorraine.fr/");
		System.out.println(testParser.getInfos());
	}

}
