package cpp2017.parser;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author David
 * 
 *         Cette queue FIFO contiendra les liens internet qu'il faut parser
 *         Cette queue suit le modèle de Singleton (une unique instance de
 *         l'objet)
 */
public final class LinkQueue {

	private static LinkQueue instance;
	private List<Parser> lParser;
	/**
	 * Ensemble des liens déjà présenté à la queue. HashSet pour test
	 * d'appartenance en O(1).
	 */
	private Set<String> histoLink;

	/**
	 * Cette queue contiendra la liste des liens envoyés par le rudder
	 */
	private TreeSet<PriorityLink> queueLien;

	private LinkQueue() {
		/**
		 * TreeSet pour ordonner les liens par ordre de pertinence
		 */
		queueLien = new TreeSet<PriorityLink>();
		lParser = new LinkedList<Parser>();
		histoLink = new HashSet<String>();
	}

	/**
	 * @return l'unique instance (singleton)
	 * 
	 */
	public static LinkQueue getInstance() {
		if (instance == null) {
			synchronized (LinkQueue.class) {
				instance = new LinkQueue();
			}
		}
		return instance;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	public void registerParser(Parser p) {
		lParser.add(p);
	}

	/**
	 * Cette fonction ajoute un lien à la fin de la queue. Elle sera appelé par
	 * le Rudder
	 * 
	 * @param lien
	 */
	public synchronized void addPriorityLink(PriorityLink lien) {
		// verrou pour l'écriture
		queueLien.add(lien);
		histoLink.add(lien.getUrl());
		this.notifyAll();
	}

	public synchronized int size() {
		return queueLien.size();
	}

	/**
	 * Cette fonction retourne et enlève le lien qui se trouve au début de la
	 * queue Elle sera appelé par les threads parser
	 * 
	 * @return PriorityLink
	 */
	public synchronized PriorityLink getLink() throws InterruptedException {
		while (queueLien.isEmpty())
			wait();

		PriorityLink linkToParse = queueLien.pollLast();
		return linkToParse; // retourne le lien le mieux classé ou null si
							// queueLien est une liste vide
	}

	public synchronized boolean alreadyParsed(String newLink) {
		for (String link : histoLink) {
			if (link.equals(newLink))
				return true;
		}
		return false;
	}

	public synchronized int getPriorityOfLastElem() {
		if (queueLien.isEmpty())
			return 0;
		return this.queueLien.first().getPriority();
	}

	public synchronized void clearAll() {
		queueLien.clear();
		histoLink.clear();
	}

	public synchronized void removeAlreadyParsed(List<String> links) {
		for (Iterator<String> it = links.iterator(); it.hasNext();) {
			String l = it.next();
			if (histoLink.contains(l))
				it.remove();
		}
	}

}