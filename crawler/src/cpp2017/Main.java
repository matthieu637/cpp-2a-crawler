package cpp2017;

import cpp2017.rudder.RudderFactory;

import java.util.LinkedList;
import java.util.List;

import cpp2017.rudder.Rudder;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RudderFactory rudderFactory=new RudderFactory();
		List<Rudder> lRudder = new LinkedList<Rudder>();
		lRudder.add(rudderFactory.getRudder(RudderFactory.TYPE_RUDDER1));
		lRudder.get(0).afficheTypeRudder();
	}

}
