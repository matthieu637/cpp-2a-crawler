package cpp2017.parser;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import cpp2017.rudder.Rudder;

/**
 * @author MathieuDEVALLE
 * @author David
 * @author Matthieu Zimmer
 * 
 *         Classe permettant de parser une page web à partir d'une url Thread
 *         différent des autres car les requêtes réseaux sont plus longues à
 *         exécuter.
 */
public abstract class Parser extends Thread {

	/**
	 * Lien qui va se faire Parser
	 */
	protected String currentLink;

	/**
	 * Le gouvernail qui recevra les nouveaux liens
	 */
	private Rudder rudder;

	/**
	 * @return les liens présents dans une page
	 * @throws IOException
	 */
	public abstract LinkedList<String> getLinks() throws IOException;

	/**
	 * TODO: à modifier plus tard
	 * 
	 * @return
	 * @throws IOException
	 */
	public abstract HashMap<String, Vector<String>> getInfos() throws IOException;

	/**
	 * Enregistre le rudder auquel transmettre les liens trouvés lors du parse.
	 * 
	 * @param r
	 * 
	 */
	public void registerRudder(Rudder r) {
		this.rudder = r;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread# run() Ce que fait le Thread lorsqu'il est en cours
	 * d'exécution
	 */
	public void run() {

		while (true) {
			try {
				// attente sur getLink
				this.changeCurrentLink(LinkQueue.getInstance().getLink().getUrl());

				// On affiche les infos trouvés
				System.out.println(Thread.currentThread().getId() + " " + currentLink);

				// parse
				List<String> links = this.getLinks();
				// pas besoin d'ajouter les liens déjà parsé, cela redonne
				// du travail au gourvernail pour rien
				LinkQueue.getInstance().removeAlreadyParsed(links);
				rudder.addLink(links);
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Change le Lien sur lequel le parse doit se faire
	 * 
	 * @param link
	 */
	private void changeCurrentLink(String link) {
		this.currentLink = link;
	}
}
