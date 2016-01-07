package aqueryum.valueformatters;

import java.util.Collection;

import aqueryum.ValueFormatter;
import aqueryum.incoming.Operator;

public class CollectionFormatter implements ValueFormatter {

	@SuppressWarnings("unchecked")
	@Override
	public String format(Object value, Operator op) {
		String s = "";
    	if(op.equals(Operator.in) || op.equals(Operator.nin)){
    		s = "(";
    		for(String e : (Collection<String>) value){
    			if(!s.equals("(")) s += ", ";
    			s += "'" + e + "'";
    		}
    		s += ")";
    	}
		return s;
	}

}
