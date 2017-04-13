package cpp2017.parser;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;

import cpp2017.parser.LinkQueue;
import cpp2017.rudder.Rudder;

/**
 * @author MathieuDEVALLE, David Classe permettant de parser une page web à
 *         partir d'une url Thread différent des autres car les requêtes réseaux
 *         sont plus longues à exécuter.
 */
public abstract class Parser extends Thread {

	private Rudder rudder;

	public abstract LinkedList<String> getLinks() throws IOException;

	public abstract HashMap<String, Vector<String>> getInfos() throws IOException;

	public void registerRudder(Rudder r) {
		this.rudder = r;
	}

	public void run() {

		while (true) {
			synchronized (LinkQueue.getInstance()) {
				try {

					while (LinkQueue.getInstance().isEmpty())
						LinkQueue.getInstance().wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				this.changeCurrentLink(LinkQueue.getInstance().getLink().getUrl());
			}
			try {
				// On affiche les infos trouvés
				System.out.println(this.getInfos());

				// On ajoute les Liens trouvés lors du Parse au rudder
				rudder.addLink(this.getLinks());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	protected abstract void changeCurrentLink(String link);
}
