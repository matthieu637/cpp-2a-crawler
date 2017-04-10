package cpp2017;

import java.io.IOException;
import cpp2017.parser.Parser;
import cpp2017.rudder.Rudder;
import cpp2017.rudder.RudderFactory;

public class Principale {

	public static void main(String[] args) throws IOException {
		RudderFactory testRudderFactory = new RudderFactory();
		Rudder testRudder1 = testRudderFactory.getRudder(1);
		System.out.println(testRudder1);
		Parser testParser = new Parser("http://arche.univ-lorraine.fr/");
		System.out.println(testParser.getInfos());

	}

}
