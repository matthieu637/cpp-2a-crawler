package cpp2017;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cpp2017.database.Connexion;
import cpp2017.parser.LinkQueue;
import cpp2017.parser.Parser;
import cpp2017.parser.ParserFactory;
import cpp2017.rudder.Rudder;
import cpp2017.rudder.RudderFactory;

public class Principale {

	public static void main(String[] args) throws IOException, InterruptedException {
		Connexion co = new Connexion();
		co.connect();
		// Creation d'un Rudder Naif, limite de nombres de liens à parser
		// déterminée dans CrawlerConfig.java
		Rudder testNaiveRudder = RudderFactory.getInstance().getRudder(RudderFactory.TYPE_NAIVE_RUDDER);
		System.out.println(testNaiveRudder);
		
		List<Parser> pool = new ArrayList<>(15);
		for (int i = 0; i < 15; i++) {
			Parser parser = ParserFactory.getInstance().getParser(ParserFactory.TYPE_JSOUP_PARSER);
			parser.registerRudder(testNaiveRudder);
			LinkQueue.getInstance().registerParser(parser);
			pool.add(parser);
		}

		testNaiveRudder.addLink(Arrays.asList("http://arche.univ-lorraine.fr/"));
		
		for (Parser p : pool)
			p.start();
		
		//attend fin des threads
		for (Parser p : pool)
			p.join();
	}

}
