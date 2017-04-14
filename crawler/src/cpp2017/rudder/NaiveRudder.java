package cpp2017.rudder;

import java.util.List;

import cpp2017.parser.LinkQueue;
import cpp2017.parser.PriorityLink;

/**
 * @author David Premier objet hérité de Rudder (à compléter) Ce rudder parse
 *         tous les liens qu'il reçoit sans en changer l'ordre
 */
public class NaiveRudder extends Rudder {

	private int nbMaxLien;
	private int compteurLiens;
	
	
	public NaiveRudder(int nbMaxLien) {
		this.nbMaxLien = nbMaxLien;
		this.compteurLiens=0;
		// constructeur à ajouter
	}

	@Override
	public String toString() {
		return "NaiveRudder";
	}

	@Override
	public void addPriorityLink(List<PriorityLink> links) {
		int len=links.size();
		if (len+this.compteurLiens > this.nbMaxLien){
			if (this.compteurLiens==this.nbMaxLien)
				return;
			links=links.subList(0, this.nbMaxLien-this.compteurLiens);
			this.compteurLiens=this.nbMaxLien;
		}
		else
			this.compteurLiens+=len;
		
		for (PriorityLink link : links) {
			// à modifier quand le constructeur de PriorityLink aura changé

			LinkQueue.getInstance().addPriorityLink(link);
		}
	}
	@Override
	public void addLink(List<String> links) {
		if (links.size() > this.nbMaxLien)
			links=links.subList(0, this.nbMaxLien);
		for (String link : links) {
			// à modifier quand le constructeur de PriorityLink aura changé
			if(!LinkQueue.getInstance().alreadyParsed(link))
			{
				LinkQueue.getInstance().addPriorityLink(new PriorityLink(link,LinkQueue.getInstance().getPriorityOfLastElem()+1));
			}
		}
	}
}
