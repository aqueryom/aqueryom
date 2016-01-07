package aqueryum.incoming;

import aqueryum.PathFinderFactory;
import aqueryum.QueryFactory;
import aqueryum.PathFinder;
import aqueryum.ValueFormatter;

public class Criterion implements QueryFactory {

    private String field;
    private Operator op;
    private Object value;

    public Criterion() {
    }
    
    public Criterion(String f, Operator op, Object v) {
        this.field = f;
        this.op = op;
        this.value = v;
    }
	
	public String joinEntities(PathFinderFactory joinFactory) {
		PathFinder pathFinder = joinFactory.getPathFinder(field);
		return pathFinder.getJoinEntities(); 
	}    
    
	public String filters(PathFinderFactory factory) {
		PathFinder pathFinder = factory.getPathFinder(field);
		return condition(pathFinder).append(pathFinder.getJoinFilters())
									.toString();
	}

	public StringBuilder condition(PathFinder f) {
		StringBuilder condition = pathAndOperator(f.getAliasAndField()); 
		if (op.hasValue()) {
			ValueFormatter formatter = f.getValueFormatter();
			condition.append(' ')
				  .append(formatter.format(value, op));
		}
		return condition;
	}

	protected StringBuilder pathAndOperator(String aliasAndField) {
		StringBuilder result = new StringBuilder();
		if(this.op.equals(Operator.contains)){
			result.append("LOWER(")
				  .append(aliasAndField)
				  .append(")");
		} else {
			result.append(aliasAndField);
		}
		return result.append(' ')
					 .append(op.getOp());
	}
	    
    public String getField() {
    	return this.field;
    }
    
    public void setField(String f) {
    	this.field = f;
    }
    
    public Operator getOperator() {
    	return this.op;
    }
    
    public void setOp(Operator op) {
    	this.op = op;
    }
    
    public Operator getOp() {
    	return this.op;
    }
    
    public void setOperator(Operator op) {
    	this.op = op;
    }
    
    public Object getValue() {
    	return this.value;
    }
    
    public void setValue(Object v) {
    	this.value = v;
    }
   public String toString(){
    	return "{'field': "+ this.field +", 'op': "+this.op+", 'value': "+this.value+"}";
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((field == null) ? 0 : field.hashCode());
		result = prime * result + ((op == null) ? 0 : op.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		Criterion other = (Criterion) obj;
		if (field == null) {
			if (other.field != null)
				return false;
		} else if (!field.equals(other.field))
			return false;
		if (op != other.op)
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
}
