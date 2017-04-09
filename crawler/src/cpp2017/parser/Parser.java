package cpp2017.parser;

import java.io.IOException;
import java.util.LinkedList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cpp2017.rudder.Rudder;
import cpp2017.rudder.RudderFactory;

public class Parser extends Thread {
	private PriorityLink currentLink;
	private RudderFactory rudderFactory;
	private Rudder rudder1;

	public Parser(String url) {
		this.currentLink = new PriorityLink(url);
		rudderFactory = new RudderFactory();
		rudder1 = rudderFactory.getRudder(RudderFactory.TYPE_RUDDER1);
	}

	public LinkedList<PriorityLink> getLinks() throws IOException {
		LinkedList<PriorityLink> LinksList = new LinkedList<PriorityLink>();
		Document page = Jsoup.connect(this.currentLink.getUrl()).get();
		Elements links = page.select("a[href]");
		for (Element link : links) {
			LinksList.add(new PriorityLink(link.attr("abs:href")));
		}

		return LinksList;
	}

	public void run() {
		while (true) {
			if (LinkQueue.getInstance().isEmpty()) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				this.changeCurrentLink(LinkQueue.getInstance().getLink());
				try {
					rudder1.addLink(this.getLinks());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	private void changeCurrentLink(PriorityLink link) {
		this.currentLink = link;
	}

}
