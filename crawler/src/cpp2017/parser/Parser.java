package cpp2017.parser;

import java.io.IOException;
import java.util.LinkedList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cpp2017.rudder.Rudder;
import cpp2017.rudder.RudderFactory;

/**
 * @author MathieuDEVALLE, David 
 * Thread différent des autres car les requêtes
 *         réseaux sont plus longues à exécuter.
 */
public class Parser extends Thread {
	private PriorityLink currentLink; // Lien qui va se faire Parser
	private RudderFactory rudderFactory; // createur de Rudder
	private Rudder rudder1; // rudders qui vont recevoir les nouveaux liens (1
							// seul pour l'instant)

	/**
	 * @param url
	 *            Constructeur à partir d'une String contenant l'url
	 */
	public Parser(String url) {
		this.currentLink = new PriorityLink(url);
		rudderFactory = new RudderFactory();
		rudder1 = rudderFactory.getRudder(RudderFactory.TYPE_RUDDER1);
	}

	/**
	 * @return LinkedList contenant les Liens repérés par le Parse
	 * @throws IOException
	 */
	public LinkedList<PriorityLink> getLinks() throws IOException {
		LinkedList<PriorityLink> LinksList = new LinkedList<PriorityLink>();
		Document page = Jsoup.connect(this.currentLink.getUrl()).get();
		Elements links = page.select("a[href]");// le tag 'a' correspond aux
												// liens
		for (Element link : links) {
			LinksList.add(new PriorityLink(link.attr("abs:href")));
		}

		return LinksList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run() Ce que fait le Thread lorsqu'il est en cours
	 * d'exécution
	 */
	public void run() {
		while (true) { // Pour l'instant il n'y a pas de conditions d'arrêt

			if (LinkQueue.getInstance().isEmpty()) { // S'il n'y a aucun lien à
														// parser dans la queue
				try {
					Thread.sleep(10); // On pause le thread pendant 10 ms et on
										// regarde de nouveau
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else { // Sinon, on change le lien qui doit être parser
				this.changeCurrentLink(LinkQueue.getInstance().getLink());

				try {
					// On ajoute les Liens trouvés lors du Parse aux rudders
					rudder1.addLink(this.getLinks());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * @param link
	 *            Change le Lien sur lequel le parse doit se faire
	 */
	private void changeCurrentLink(PriorityLink link) {
		this.currentLink = link;
	}

}
