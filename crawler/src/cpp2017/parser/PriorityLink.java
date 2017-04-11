package cpp2017.parser;

public class PriorityLink implements Comparable<PriorityLink> { //Pour l'instant pas d'outil de comparaison

	private String url;
	private int priority;
	
	public PriorityLink(String url,int priority){
		// Pour l'instant, on ne met pas d'éléments de comparaison
		// dans le constructeur parce qu'on a besoin d'un Parser 
		// opérationnel pour celà.
		this.url = url;
		this.priority=priority;
		
	}
	@Override
	public int compareTo(PriorityLink link) {
		// TODO Auto-generated method stub
		return link.getPriority()-this.priority;
		//Pour l'instant la LinkQueue fait un simple FIFO
		
	}
	
	@Override
	public String toString() {
		return "PriorityLink [url=" + url + "]";
	}
	public String getUrl() {
		// TODO Auto-generated method stub
		return url;
	}
	public int getPriority(){
		return priority;
	}
}
