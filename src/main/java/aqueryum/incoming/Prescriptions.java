package aqueryum.incoming;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import aqueryum.QueryFactory;

public class Prescriptions extends Criteria {
	
	public final static String ORDER_BY_CLAUSE = " ORDER BY ";
	public final static String ORDER_SEP = ", ";
    
	public final static String AND_CLAUSE = " AND ";
	public final static String OR_CLAUSE = " OR ";
	
	public final static String WHERE_CLAUSE = " WHERE ";

    private Collection<Ordering> orderings = Collections.emptyList();
    private int max;

    public Prescriptions() {
    	super();
    }
    
    public Prescriptions(Collection<QueryFactory> criteria, Collection<Ordering> orderings) {
    	super();
        this.setCriteria(criteria);
        this.setOrderings(orderings);
        this.setClause(AND_CLAUSE);
    }
    
    public Prescriptions(Collection<QueryFactory> criteria, Collection<Ordering> orderings, int max) {
    	super();
        this.setCriteria(criteria);
        this.setOrderings(orderings);
        this.setClause(AND_CLAUSE);
        this.setMax(max);
    }
    
    public Prescriptions(Collection<QueryFactory> criteria, Collection<Ordering> orderings, String clause) {
    	super();
        this.setCriteria(criteria);
        this.setOrderings(orderings);
        this.setClause(clause);
    }
    
    public Prescriptions(Collection<QueryFactory> criteria, Collection<Ordering> orderings, String clause, int max) {
    	super();
        this.setCriteria(criteria);
        this.setOrderings(orderings);
        this.setClause(clause);
        this.setMax(max);
    }
    
    public Collection<Ordering> getOrderings() {
        return this.orderings;
    }
    
    public void setOrderings(Collection<Ordering> orderings) {
        this.orderings = Collections.unmodifiableList(new ArrayList<>(orderings));
    }

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}
	
	public String toString(){
		return "{'criteria' : "+ this.getCriteria().toString() +", 'ordering': "+ this.orderings.toString() +", 'max': "+ this.max +"}";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + max;
		result = prime * result
				+ ((orderings == null) ? 0 : orderings.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prescriptions other = (Prescriptions) obj;
		if (max != other.max)
			return false;
		if (orderings == null) {
			if (other.orderings != null)
				return false;
		} else if (!orderings.equals(other.orderings))
			return false;
		return true;
	}
}
