package cpp2017.aggregation;

/**
 * @author David
 * Cette objet permet de créer des instances des différents
 * objets qui héritent de la class abstraite Aggregation.
 * Celà permet de faire notamment du polymorphisme.
 * A compléter: Même principe que le Rudder
 */
public class AggregationFactory {
	public static final int TYPE_AGGREGATION1=1;
	
	public Aggregation getAggregation(int typeAggregation){
		Aggregation a;
		switch (typeAggregation){
		case TYPE_AGGREGATION1:
			a=new Aggregation1();
			break;
		default:
			throw new IllegalArgumentException("Type d'aggrégation inconnu");
		}
	return a;
	}
}
