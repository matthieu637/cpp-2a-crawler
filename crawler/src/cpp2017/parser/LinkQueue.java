package cpp2017.parser;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public final class LinkQueue {

	private static LinkQueue instance;
	private Queue<String> queueLien; 
	
	private LinkQueue(){
		queueLien= new ConcurrentLinkedQueue<String>(); 
		//ConcurrentLinkedQueue pour empêcher l'accès en même temps
		//à la queue (par exemple deux Threads Parser qui veulent prendre
		//l'élément en front de la queue en même temps)
		//A voir s'il y a mieux
	}
	
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
	
	public void addLink(String lien){
		queueLien.add(lien);
	}
	
	public void getLink(String lien){
		queueLien.poll(); //retourne null si vide
	}

}
