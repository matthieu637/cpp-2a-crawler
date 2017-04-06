package cpp2017.rudder;

public class RudderFactory {
	public static final int TYPE_RUDDER1=1;
	
	public Rudder getRudder(int typeRudder){
		Rudder r;
		switch (typeRudder){
			case TYPE_RUDDER1:
				r=new Rudder1();
				break;
			default:
				throw new IllegalArgumentException("Type de rudder inconnu");
		}
	return r;
	}
}
