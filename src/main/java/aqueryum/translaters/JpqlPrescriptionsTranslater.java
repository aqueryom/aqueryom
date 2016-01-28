package aqueryum.translaters;


import java.util.Collection;

import aqueryum.PathFinderFactory;
import aqueryum.incoming.Ordering;
import aqueryum.incoming.Prescriptions;
import static aqueryum.incoming.Prescriptions.WHERE_CLAUSE;
import static aqueryum.incoming.Prescriptions.AND_CLAUSE;

public class JpqlPrescriptionsTranslater extends AbstractPrescriptionsTranslater {

	public final static String ORDER_BY_CLAUSE = " ORDER BY ";
	public final static String ORDER_SEP = ", ";

	

	public JpqlPrescriptionsTranslater(PathFinderFactory f) {
        super(f);
    }
	
    @Override
	public StringBuilder translate(Prescriptions prescriptions, StringBuilder b, boolean startsWithWhere) {
       	String entities = prescriptions.entities(this.factory);
		b.append(entities);
		String filters = prescriptions.filters(this.factory);
    	if(filters != null && !filters.trim().equals("")) {
    		b.append(startsWithWhere ? WHERE_CLAUSE : AND_CLAUSE)
    		 .append(filters);
    	}
        return translate(prescriptions.getOrderings(), b);
    }

	protected StringBuilder translate(Collection<Ordering> orderings,
			StringBuilder b) {
		String sep = ORDER_BY_CLAUSE;
		for (Ordering o : orderings) {
            b.append(sep);
            b.append(o.visit(this.factory));
            sep = ORDER_SEP;
        }
        return b;
	}

	

}
