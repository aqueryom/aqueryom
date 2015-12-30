package aqueryum.recipients;

import java.util.Collection;
import java.util.Date;

import aqueryum.EntitiesFactory;
import aqueryum.FieldFactory;
import aqueryum.SearchCondition;
import aqueryum.SearchField;

public class Criterion implements SearchCondition {

    private String field;
    private Operator op;
    private Object value;
    
    public enum Operator {
        eq("="), ne("<>"), gt(">"), lt("<"), ge(">="), le("<="),
        isNull("IS NULL", false), notNull("IS NOT NULL", false),
        contains("LIKE"),
        in("IN"), nin("NOT IN");

        private final String op;
        private final boolean hasValue;

        private Operator(String op) {
            this(op, true);
        }
        private Operator(String op, boolean hasValue) {
            this.op = op;
            this.hasValue = hasValue;
        }

        public String getOp() {
            return this.op;
        }
        public boolean hasValue() {
        	return this.hasValue;
        }

        public static Operator fromString(String s) {
        	Operator op = null;
        	for (Operator o : values()) {
        		if ((o.name().equalsIgnoreCase(s)) || (o.op.equalsIgnoreCase(s))) {
        			op = o;
        			break;
        		}
        	}
        	return op;
        }
    }

    public Criterion() {
    }
    
    public Criterion(String f, Operator op, Object v) {
        this.field = f;
        this.op = op;
        this.value = v;
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
    
	public String visit(FieldFactory factory) {
		StringBuilder result = new StringBuilder();
		SearchField f = factory.getField(this.getField());
		if(this.op.equals(Operator.contains)){
			result.append("LOWER(");
			result.append(f.getAliasAndField());
			result.append(")");
		} else {
			result.append(f.getAliasAndField());
		}
		result.append(' ').append(op.getOp());
		if (op.hasValue()) {
			result.append(' ').append(this.escapeValue(this.value, f, op));
		}
		return result.toString();
	}
	
	protected String escapeValue(Object value, SearchField f, Operator op) {
    	return this.toString(value, f.getType(), op);
    }
	
	public String moreEntities(EntitiesFactory factory) {
		return factory.getEntity(field); 
	}    

	public String moreFilters(EntitiesFactory factory) {
		return factory.getFilters(field) // joints
				 + " AND " + visit(factory); // filter of criterion
	}

    @SuppressWarnings("unchecked")
    private String toString(Object v, Class<?> type, Operator op) {
        String s = "";
        if (CharSequence.class.isAssignableFrom(type)) {
        	if(op.equals(Operator.contains)){
        		v = "%" + v.toString().toLowerCase() + "%";
            }
            s = "'" + v + '\'';
        }
        else if (Date.class.isAssignableFrom(type)) {
            s = "to_date('" + v + "', 'YYYY-MM-DD')";
        }
        else if (Collection.class.isAssignableFrom(type)){
        	if(op.equals(Operator.in) || op.equals(Operator.nin)){
        		s = "(";
        		for(String e : (Collection<String>) v){
        			if(!s.equals("(")) s += ", ";
        			s += "'" + e + "'";
        		}
        		s += ")";
        	}
        }
        else {
            s = String.valueOf(v);
        }
        return s;
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
