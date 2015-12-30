package aqueryum.builders;


import aqueryum.FieldFactory;
import aqueryum.recipients.Ordering;
import aqueryum.recipients.SearchCriteria;

public class SqlRequestBuilder extends SearchVisitor {
	
	

	public SqlRequestBuilder(FieldFactory f) {
        super(f);
    }
	
    @Override
	public StringBuilder visit(SearchCriteria c, StringBuilder b, boolean startsWithWhere) {
    	String ctr = c.visit(this.factory);
    	if(ctr != null && !ctr.trim().equals("")) {
    		b.append((startsWithWhere) ? SearchCriteria.WHERE_CLAUSE : SearchCriteria.AND_CLAUSE);
    		b.append(ctr);
    	}
        String sep = SearchCriteria.ORDER_BY_CLAUSE;
        for (Ordering o : c.getOrderings()) {
            b.append(sep);
            b.append(o.visit(this.factory));
            sep = SearchCriteria.ORDER_SEP;
        }
        return b;
    }

	

}
