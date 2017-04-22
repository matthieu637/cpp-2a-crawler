package cpp2017.parser;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

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
	private final ReadWriteLock mutex_ordre = new ReentrantReadWriteLock();
	private final Lock mutex_ordre_read = mutex_ordre.readLock();
	private final Lock mutex_ordre_write = mutex_ordre.writeLock();
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
		mutex_ordre_write.lock();
		lParser.add(p);
		mutex_ordre_write.unlock();
	}

	/**
	 * Cette fonction ajoute un lien à la fin de la queue. Elle sera appelé par
	 * le Rudder
	 * 
	 * @param lien
	 */
	public void addPriorityLink(PriorityLink lien) {
		// verrou pour l'écriture
		mutex_ordre_write.lock();
		queueLien.add(lien);
		histoLink.add(lien.getUrl());
		synchronized(this){
			this.notify();
		}
		mutex_ordre_write.unlock();
	}

	public int size() {
		mutex_ordre_read.lock();
		int l = queueLien.size();
		mutex_ordre_read.unlock();
		return l;
	}

	/**
	 * Cette fonction retourne et enlève le lien qui se trouve au début de la
	 * queue Elle sera appelé par les threads parser
	 * 
	 * @return PriorityLink
	 */
	public PriorityLink getLink() throws InterruptedException {
		mutex_ordre_write.lock();
		while (queueLien.isEmpty()){
			mutex_ordre_write.unlock();
			synchronized(this){
				this.wait();
			}
			mutex_ordre_write.lock();
		}
		PriorityLink linkToParse = queueLien.pollLast();
		mutex_ordre_write.unlock();
		return linkToParse; // retourne le lien le mieux classé ou null si
							// queueLien est une liste vide
	}

	public boolean alreadyParsed(String newLink) {
		mutex_ordre_read.lock();
		for (String link : histoLink) {
			if (link.equals(newLink)){
				mutex_ordre_read.unlock();
				return true;
			}
		}
		mutex_ordre_read.unlock();
		return false;
	}

	public int getPriorityOfLastElem() {

		mutex_ordre_read.lock();
		if (queueLien.isEmpty()){
			mutex_ordre_read.unlock();
			return 0;
		}
		int p= this.queueLien.first().getPriority();
		mutex_ordre_read.unlock();
		return p;
	}

	public void clearAll() {
		mutex_ordre_write.lock();
		queueLien.clear();
		histoLink.clear();
		mutex_ordre_write.unlock();
	}

	public void removeAlreadyParsed(List<String> links) {
		mutex_ordre_write.lock();
		for (Iterator<String> it = links.iterator(); it.hasNext();) {
			String l = it.next();
			if (histoLink.contains(l))
				it.remove();
		}
		mutex_ordre_write.unlock();
	}

}