package cpp2017;

public class CrawlerConfig {
	
	private static CrawlerConfig instance;
	public final int NB_MAX_LIEN_NAIVE_RUDDER=50;

	/** Constructeur Privé pour empêcher une construction 
	 * en dehors de la classe (design pattern singleton)
	 */
	private CrawlerConfig(){
	}
	
	/**
	 * @return l'unique instance (singleton)
	 * 
	 */
	public static CrawlerConfig getInstance() {
		if (instance == null){
			synchronized (CrawlerConfig.class){
				instance = new CrawlerConfig();
			}
		}
		return instance;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
}
