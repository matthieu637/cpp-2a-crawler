package cpp2017.parser;

public class PriorityLink implements Comparable { //Pour l'instant pas d'outil de comparaison

	private String url;
	
	public PriorityLink(String url){
		// Pour l'instant, on ne met pas d'éléments de comparaison
		// dans le constructeur parce qu'on a besoin d'un Parser 
		// opérationnel pour celà.
		this.url = url;
	}
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return -1; // pas 0, sinon les éléments ne sont pas ajoutés
		//il faudra biensur un retour sophistiqué, le signe du retour
		//indique l'ordre dans lequel vont être classé les url
		//l'un par rapport à l'autre
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
	
}
