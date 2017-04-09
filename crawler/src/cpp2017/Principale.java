package cpp2017;

import java.io.IOException;
import cpp2017.parser.Parser;

public class Principale {

	public static void main(String[] args) throws IOException {
		Parser testParser=new Parser("http://arche.univ-lorraine.fr");
		System.out.println(testParser.getLinks());

	}

}
