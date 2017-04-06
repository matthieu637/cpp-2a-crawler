package cpp2017.aggregation;

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
