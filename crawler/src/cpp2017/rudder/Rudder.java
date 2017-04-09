package cpp2017.rudder;

import java.util.List;

/**
 * @author David
 * Tout les "gouvernails" hériteront de cette classe mère abstraite
 * Celà permettra le polymorphisme
 */
public abstract class Rudder {
	public abstract void addLink(List<String> links);
	public abstract String toString();
}
