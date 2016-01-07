package aqueryum.valueformatters;

import aqueryum.ValueFormatter;
import aqueryum.incoming.Operator;

public class CharSequenceFormatter implements ValueFormatter {

	@Override
	public String format(Object value, Operator op) {
       	if(op.equals(Operator.contains)){
        		value = "%" + value.toString().toLowerCase() + "%";
        }
		return "'" + value + '\'';
	}

}
