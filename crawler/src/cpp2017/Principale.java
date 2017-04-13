package cpp2017;

import java.io.IOException;
import java.util.Arrays;
import cpp2017.database.Connexion;
import cpp2017.parser.LinkQueue;
import cpp2017.parser.Parser;
import cpp2017.parser.ParserFactory;
import cpp2017.rudder.Rudder;
import cpp2017.rudder.RudderFactory;

public class Principale {

	public static void main(String[] args) throws IOException {
		Connexion co = new Connexion();
		co.connect();
		// Creation d'un Rudder Naif, limite de nombres de liens à parser
		// déterminée dans CrawlerConfig.java
		Rudder testNaiveRudder = RudderFactory.getInstance().getRudder(RudderFactory.TYPE_NAIVE_RUDDER);
		System.out.println(testNaiveRudder);
		Parser testJsoupParser = ParserFactory.getInstance().getParser(ParserFactory.TYPE_JSOUP_PARSER);
		testJsoupParser.registerRudder(testNaiveRudder);
		testJsoupParser.start();
		LinkQueue.getInstance().registerParser(testJsoupParser);

		testNaiveRudder.addLink(Arrays.asList("http://arche.univ-lorraine.fr/"));

	}

}
