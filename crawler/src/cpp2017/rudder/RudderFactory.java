package cpp2017.rudder;

import cpp2017.CrawlerConfig;

/**
 * @author David
 * Cette objet permet de créer des instances des différents
 * objets qui héritent de la class abstraite Rudder.
 * Celà permet de faire notamment du polymorphisme.
 */
public final class RudderFactory {
	
	private static RudderFactory instance;
	/**
	 * Ici il faut mettre un nom de variable par nombre de Rudders 
	 * que l'on a créé
	 */
	public static final int TYPE_NAIVE_RUDDER=1;
	
	/** Constructeur Privé pour empêcher une construction 
	 * en dehors de la classe (design pattern singleton)
	 */
	private RudderFactory(){
	}
	
	/**
	 * @return l'unique instance (singleton)
	 * 
	 */
	public static RudderFactory getInstance() {
		if (instance == null){
			synchronized (RudderFactory.class){
				instance = new RudderFactory();
			}
		}
		return instance;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
	
	/**
	 * @param typeRudder
	 * @return un objet hérité de Rudder
	 * En fonction du type entré en paramètre, la factory
	 * va créer le Rudder approprié
	 */
	
	public Rudder getRudder(int typeRudder){
		Rudder r;
		switch (typeRudder){
			case TYPE_NAIVE_RUDDER: //si le type demandé est TYPE_RUDDER1
				r=new NaiveRudder(CrawlerConfig.getInstance().NB_MAX_LIEN_NAIVE_RUDDER); //on crée un objet Rudder1
				break;
			default:
				throw new IllegalArgumentException("Type de rudder inconnu");
		}
	return r; //on retourne l'objet choisi
	}
}
