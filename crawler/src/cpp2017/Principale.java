package cpp2017;

import java.io.IOException;
import cpp2017.parser.Parser;
import cpp2017.rudder.Rudder;
import cpp2017.rudder.RudderFactory;


public class Principale {

	public static void main(String[] args) throws IOException {
		//Creation d'un Rudder Naif, limite de nombres de liens à parser déterminée dans CrawlerConfig.java
		Rudder testRudder1 = RudderFactory.getInstance().getRudder(RudderFactory.TYPE_NAIVE_RUDDER);
		System.out.println(testRudder1);
		Parser testParser = new Parser("http://arche.univ-lorraine.fr/");
		System.out.println(testParser.getInfos());
	}

}
