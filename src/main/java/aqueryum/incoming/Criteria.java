package aqueryum.incoming;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import aqueryum.PathFinderFactory;
import aqueryum.FilterFactory;


public class Criteria implements FilterFactory {

	private Collection<FilterFactory> criteria = Collections.emptyList();
	private String clause;

    public Criteria() {
    }
    
    public Criteria(Collection<FilterFactory> criteria) {
        this.setCriteria(criteria);
    }
	
    public Criteria(Collection<FilterFactory> criteria, String clause) {
		this(criteria);
		this.clause = clause;
	}
	
	@Override
	public String filters(PathFinderFactory pathFinderFactory) {
		StringBuilder result = new StringBuilder();
		String clause = "";
		for (FilterFactory filterFactory : this.criteria) {
			result.append(clause);
			boolean expr = (filterFactory instanceof Criteria);
			if (expr) { result.append('('); }
			result.append(filterFactory.filters(pathFinderFactory));
			if (expr) { result.append(')'); }
			clause = this.clause != null ? this.clause : Prescriptions.AND_CLAUSE;
		}
		return result.toString();
	}

	public Set<String> joinEntities(PathFinderFactory factory) { 
	    Set<String> entities =  new HashSet<String>() ;
		for (FilterFactory filterFactory : this.criteria) {
			entities.addAll(filterFactory.joinEntities(factory));
		}
		return entities;
	}

	public Set<String> joinFilters(PathFinderFactory factory) {
	    Set<String> joinFilters =  new HashSet<String>() ;
//		for (FilterFactory filterFactory : this.criteria) {
//			joinFilters.addAll(filterFactory.joinEntities(factory));
//		}
		return joinFilters;
	}


	public Collection<FilterFactory> getCriteria() {
		return criteria;
	}

	public void setCriteria(Collection<FilterFactory> criteria) {
		this.criteria = Collections.unmodifiableList(new ArrayList<>(criteria));
	}
	
	public String getClause() {
		return clause;
	}

	public void setClause(String clause) {
		this.clause = clause;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clause == null) ? 0 : clause.hashCode());
		result = prime * result
				+ ((criteria == null) ? 0 : criteria.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Criteria other = (Criteria) obj;
		if (clause == null) {
			if (other.clause != null)
				return false;
		} else if (!clause.equals(other.clause))
			return false;
		if (criteria == null) {
			if (other.criteria != null)
				return false;
		} else if (!criteria.equals(other.criteria))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Criteria ["
				+ (criteria != null ? "criteria=" + criteria + ", " : "")
				+ (clause != null ? "clause=" + clause : "") + "]";
	}
}
