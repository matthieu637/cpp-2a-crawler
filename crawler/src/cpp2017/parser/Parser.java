package cpp2017.parser;

import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Parser {
	/**
	 * @author MatthieuDEVALLE 
	 * Classe permettant de parser une page web à partir
	 * d'un attribut url
	 */
	String url;

	public Parser(String url) {
		this.url = url;
	}

	// public String getTitle() throws IOException{
	// Document doc = Jsoup.connect("http://arche.univ-lorraine.fr/").get();
	// return new String(doc.title());
	// }
	/**
	 * @return
	 * @throws IOException
	 *             On retourne une HashMap contenant les objets java. Pour
	 *             récupérer les liens, on se connecte avec jsoup au site et on
	 *             récupère les balises <a href="">, c'est à dire les balises
	 *             HTML qui représentent les liens. Idem pour les autres catégories
	 */
	public HashMap<String, Vector<String>> getInfos() throws IOException {
		HashMap<String, Vector<String>> contentParse = new HashMap<String, Vector<String>>();
		Vector<String> linksList = new Vector<String>();
		Vector<String> strongList = new Vector<String>();
		Vector<String> H1List = new Vector<String>();
		Vector<String> titreList = new Vector<String>(1);
		Document page = Jsoup.connect(this.url).get();
		String titre = page.title();
		Elements titreH1s = page.select("h1");
		Elements strongs = page.select("strong");
		Elements links = page.select("a[href]");

		titreList.add(titre);
		for (Element titreH1 : titreH1s) {
			H1List.add(titreH1.attr("h1"));
		}
		for (Element link : links) {
			linksList.add(link.attr("abs:href"));
		}
		for (Element strong : strongs) {
			strongList.add(strong.attr("strong"));
		}
		contentParse.put("titre", titreList);
		contentParse.put("h1", H1List);
		contentParse.put("strong", strongList);
		contentParse.put("liens", linksList);
		return contentParse;

	}

}
