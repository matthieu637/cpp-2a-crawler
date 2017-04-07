package cpp2017.parser;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author David
 * Cette queue FIFO contiendra les liens internet qu'il faut parser
 * Cette queue suit le modèle de Singleton (une unique instance de l'objet)
 */
public final class LinkQueue {

	private static LinkQueue instance;
	
	/**
	 * Cette queue contiendra la liste des liens envoyés par le rudder
	 */
	private Queue<String> queueLien; 
	
	private LinkQueue(){
		queueLien= new ConcurrentLinkedQueue<String>(); 
		//ConcurrentLinkedQueue pour empêcher l'accès en même temps
		//à la queue (par exemple deux Threads Parser qui veulent prendre
		//l'élément en front de la queue en même temps)
		//A voir s'il y a mieux
	}
	
	
	/**
	 * @return l'unique instance (singleton)
	 * 
	 */
	public static LinkQueue getInstance() {
		if (instance == null){
			synchronized (LinkQueue.class){
				instance = new LinkQueue();
			}
		}
		return instance;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
	
	/**
	 * @param String
	 * Cette fonction ajoute un lien à la fin de la queue
	 * Elle sera appelé par le Rudder
	 */
	public void addLink(String lien){
		queueLien.add(lien);
	}
	
	/**
	 * @return String
	 * Cette fonction retourne et enlève le lien qui se trouve au début de la queue
	 * Elle sera appelé par les threads parser 
	 * A voir si on a besoin de synchronized si queueLien est une ConcurrentLinkedQueue
	 */
	public String getLink(){
		return queueLien.poll(); //retourne null si vide
	}

}
