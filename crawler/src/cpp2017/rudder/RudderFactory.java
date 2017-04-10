package cpp2017.rudder;

/**
 * @author David
 * Cette objet permet de créer des instances des différents
 * objets qui héritent de la class abstraite Rudder.
 * Celà permet de faire notamment du polymorphisme.
 */
public class RudderFactory {
	
	/**
	 * Ici il faut mettre un nom de variable par nombre de Rudders 
	 * que l'on a créé
	 */
	public static final int TYPE_RUDDER1=1;
	
	/**
	 * @param typeRudder
	 * @return un objet hérité de Rudder
	 * En fonction du type entré en paramètre, la factory
	 * va créer le Rudder approprié
	 */
	public Rudder getRudder(int typeRudder){
		Rudder r;
		switch (typeRudder){
			case TYPE_RUDDER1: //si le type demandé est TYPE_RUDDER1
				r=new Rudder1(); //on crée un objet Rudder1
				break;
			default:
				throw new IllegalArgumentException("Type de rudder inconnu");
		}
	return r; //on retourne l'objet choisi
	}
}
