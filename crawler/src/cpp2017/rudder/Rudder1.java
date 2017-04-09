package cpp2017.rudder;

import java.util.List;

import cpp2017.parser.LinkQueue;
import cpp2017.parser.PriorityLink;

/**
 * @author David
 * Premier objet hérité de Rudder (à compléter)
 */
public class Rudder1 extends Rudder {

	public Rudder1(){
		//constructeur à ajouter
	}
	
	
	@Override
	public String toString() {
		return "Rudder1";
	}
	
	@Override
	public void addLink(List<PriorityLink> links) {
		for(PriorityLink link:links){
			//à modifier quand le constructeur de PriorityLink aura changé
			LinkQueue.getInstance().addPriorityLink(link);
		}
	}
}
