package aqueryum.recipients;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import aqueryum.EntitiesFactory;
import aqueryum.FieldFactory;
import aqueryum.SearchCondition;


public class Criteria implements SearchCondition {

	private Collection<SearchCondition> criteria = Collections.emptyList();
	private String clause;

    public Criteria() {
    }
    
    public Criteria(Collection<SearchCondition> criteria) {
        this.setCriteria(criteria);
    }
	
    public Criteria(Collection<SearchCondition> criteria, String clause) {
		this(criteria);
		this.clause = clause;
	}

	public Collection<SearchCondition> getCriteria() {
		return criteria;
	}

	public void setCriteria(Collection<SearchCondition> criteria) {
		this.criteria = Collections.unmodifiableList(new ArrayList<>(criteria));
	}
	
	public String getClause() {
		return clause;
	}

	public void setClause(String clause) {
		this.clause = clause;
	}
	
	@Override
	public String visit(FieldFactory factory) {
		StringBuilder result = new StringBuilder();
		String clause = "";
		for (SearchCondition sc : this.criteria) {
			result.append(clause);
			boolean expr = (sc instanceof Criteria);
			if (expr) { result.append('('); }
			result.append(sc.visit(factory));
			if (expr) { result.append(')'); }
			clause = this.clause != null ? this.clause : SearchCriteria.AND_CLAUSE;
		}
		return result.toString();
	}

	public String moreEntities(EntitiesFactory factory) { 
		StringBuilder result = new StringBuilder();
		for (SearchCondition sc : this.criteria) {
			result.append(sc.moreEntities(factory));
		}
		return result.toString();
	}

	public String moreFilters(EntitiesFactory factory) {
		StringBuilder result = new StringBuilder();
		for (SearchCondition sc : this.criteria) {
			result.append(sc.moreFilters(factory));
		}
		return result.toString();
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

}
