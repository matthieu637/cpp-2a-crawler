package cpp2017.rudder;

import java.util.List;

import cpp2017.parser.PriorityLink;

/**
 * @author David Tout les "gouvernails" hériteront de cette classe mère
 *         abstraite Celà permettra le polymorphisme
 */
public abstract class Rudder {
	public abstract void addLink(List<String> linkedListOfLink);
	public abstract void addPriorityLink(List<PriorityLink> links);

	public abstract String toString();
}
