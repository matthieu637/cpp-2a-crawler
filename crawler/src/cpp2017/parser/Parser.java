package cpp2017.parser;

import java.io.IOException;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Parser {
	String url;

	public Parser(String url) {
		this.url = url;
	}
	//public String getTitle() throws IOException{
	//	Document doc = Jsoup.connect("http://arche.univ-lorraine.fr/").get();
	//	return new String(doc.title());
	//}
	public HashMap<Integer,String> getLinks() throws IOException{
		HashMap<Integer,String> LinksList=new HashMap<Integer, String>();
		int i=0;
		Document page = Jsoup.connect(this.url).get();
		Elements links = page.select("a[href]");
		for (Element link : links) {
			LinksList.put(i,link.attr("abs:href"));
			i++;}

		return LinksList;


	}



}
