package cpp2017;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import cpp2017.parser.Parser;
import cpp2017.rudder.Rudder1;

public class main {

	public static void main(String[] args) throws IOException {
		Rudder1 testRudder=new Rudder1();
		System.out.println(testRudder.toString());
		
		Parser testParser=new Parser("http://arche.univ-lorraine.fr");
		System.out.println(testParser.getLinks());
	}

}