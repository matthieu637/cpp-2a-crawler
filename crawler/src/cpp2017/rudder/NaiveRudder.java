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

	public NaiveRudder(int nbMaxLien) {
		this.nbMaxLien = nbMaxLien;
		// constructeur à ajouter
	}

	@Override
	public String toString() {
		return "NaiveRudder";
	}

	@Override
	public void addLink(List<PriorityLink> links) {
		if (links.size() > this.nbMaxLien)
			links=links.subList(0, this.nbMaxLien);
		for (PriorityLink link : links) {
			// à modifier quand le constructeur de PriorityLink aura changé

			LinkQueue.getInstance().addPriorityLink(link);
		}
	}
}
