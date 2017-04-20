package cpp2017.aggregation;

import java.util.ArrayList;
import java.util.LinkedList;

public class Aggregation1 extends Aggregation {

	// attributs à ajouter
	ArrayList<String> lTitres;
	ArrayList<LinkedList<String>> lContenu;

	public Aggregation1() {
		// constructeur à ajouter
	}

	// fonctions à ajouter
	public void addTable(ArrayList<String> titre, ArrayList<LinkedList<String>> contenu) {
		lTitres.addAll(titre);
		lContenu.addAll(contenu);
	}
	
	public ArrayList<String> getTitles(){
		return lTitres;
	}
	
	public ArrayList<LinkedList<String>> getContenu(){
		return lContenu;
	}
	
	public void afficheTable(){
		for(int i=0;i<lTitres.size();i++){
			System.out.println(lTitres.get(i)+" : ");
			LinkedList<String> l= lContenu.get(i);
			for(String element:l){
				System.out.print(" "+element+" ,");
			}
		}
	}
}
