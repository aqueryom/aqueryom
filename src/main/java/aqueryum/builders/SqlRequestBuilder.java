package aqueryum.builders;


import aqueryum.PathFinderFactory;
import aqueryum.incoming.Ordering;
import aqueryum.incoming.Prescriptions;

public class SqlRequestBuilder extends SearchVisitor {
	
	

	public SqlRequestBuilder(PathFinderFactory f) {
        super(f);
    }
	
    @Override
	public StringBuilder visit(Prescriptions c, StringBuilder b, boolean startsWithWhere) {
    	String ctr = c.filters(this.factory);
    	if(ctr != null && !ctr.trim().equals("")) {
    		b.append((startsWithWhere) ? Prescriptions.WHERE_CLAUSE : Prescriptions.AND_CLAUSE);
    		b.append(ctr);
    	}
        String sep = Prescriptions.ORDER_BY_CLAUSE;
        for (Ordering o : c.getOrderings()) {
            b.append(sep);
            b.append(o.visit(this.factory));
            sep = Prescriptions.ORDER_SEP;
        }
        return b;
    }

	

}
