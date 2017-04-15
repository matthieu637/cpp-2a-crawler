package cpp2017.parser;

public final class ParserFactory {

	private static ParserFactory instance;
	/**
	 * Ici il faut mettre un nom de variable par nombre de Rudders que l'on a
	 * créé
	 */
	public static final int TYPE_JSOUP_PARSER = 1;

	/**
	 * Constructeur Privé pour empêcher une construction en dehors de la classe
	 * (design pattern singleton)
	 */
	private ParserFactory() {
	}

	/**
	 * @return l'unique instance (singleton)
	 * 
	 */
	public static ParserFactory getInstance() {
		if (instance == null) {
			synchronized (ParserFactory.class) {
				instance = new ParserFactory();
			}
		}
		return instance;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	/**
	 * En fonction du type entré en paramètre, la factory va créer le Rudder
	 * approprié
	 * 
	 * @param typeParser
	 * @return un objet hérité de Parser
	 */
	public Parser getParser(int typeParser) {
		Parser r;
		switch (typeParser) {
		case TYPE_JSOUP_PARSER: // si le type demandé est TYPE_RUDDER1
			r = new JsoupParser(); // on crée un objet Rudder1
			break;
		default:
			throw new IllegalArgumentException("Type de rudder inconnu");
		}
		return r; // on retourne l'objet choisi
	}

}
