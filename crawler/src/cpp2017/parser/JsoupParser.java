package cpp2017.parser;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



/**
 * @author MathieuDEVALLE, David 
 * Classe permettant de parser une page web à partir
 * d'une url
 * Thread différent des autres car les requêtes
 *         réseaux sont plus longues à exécuter.
 */
public class JsoupParser extends Parser {
	private String currentLink; // Lien qui va se faire Parser

	/**
	 * @param url
	 *            Constructeur à partir d'une String contenant l'url
	 */
	public JsoupParser() {
	}

	/**
	 * @return LinkedList contenant les Liens repérés par le Parse
	 * @throws IOException
	 */
	public LinkedList<String> getLinks() throws IOException {
		LinkedList<String> LinksList = new LinkedList<String>();
		Document page = Jsoup.connect(this.currentLink).get();
		Elements links = page.select("a[href]");// le tag 'a' correspond aux
												// liens
		for (Element link : links) {
			//LinksList.add(new PriorityLink(link.attr("abs:href"),0));
			LinksList.add(link.attr("abs:href"));
		}

		return LinksList;
	}

	/**
	 * @return
	 * @throws IOException
	 *             On retourne une HashMap contenant les objets java. Pour
	 *             récupérer les liens, on se connecte avec jsoup au site et on
	 *             récupère les balises &lt;a href=""&gt;, c'est à dire les balises
	 *             HTML qui représentent les liens. Idem pour les autres catégories
	 */
	public HashMap<String, Vector<String>> getInfos() throws IOException {
		HashMap<String, Vector<String>> contentParse = new HashMap<String, Vector<String>>();
		Vector<String> linksList = new Vector<String>();
		Vector<String> strongList = new Vector<String>();
		Vector<String> H1List = new Vector<String>();
		Vector<String> titreList = new Vector<String>(1);
    
		Document page = Jsoup.connect(this.currentLink).get();
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
	/**
	 * @param link
	 *            Change le Lien sur lequel le parse doit se faire
	 */
	protected void changeCurrentLink(String link) {
		this.currentLink = link;
	}
}