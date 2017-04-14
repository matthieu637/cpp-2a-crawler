package cpp2017.parser;

import java.util.LinkedList;
import java.util.List;
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
	private List<String> histoLink;

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
		histoLink = new LinkedList<String>();
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
		//verrou pour l'écriture
		queueLien.add(lien);
		histoLink.add(lien.getUrl());
		this.notifyAll();
	}

	/**
	 * @return PriorityLink Cette fonction retourne et enlève le lien qui se
	 *         trouve au début de la queue Elle sera appelé par les threads
	 *         parser A voir si on a besoin de synchronized si queueLien est une
	 *         ConcurrentLinkedQueue
	 */
	public int size() {
		return queueLien.size();
	}

	public PriorityLink getLink() {
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

	public boolean isEmpty() {
		return queueLien.isEmpty();
	}

	public synchronized int getPriorityOfLastElem() {
		if (this.isEmpty())
			return 0;
		return this.queueLien.first().getPriority();
	}

	public void clearAll() {
		queueLien.clear();
		histoLink.clear();
	}

}