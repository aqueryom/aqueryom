package aqueryum.valueformatters;

import aqueryum.ValueFormatter;
import aqueryum.incoming.Operator;

public class SimpleFormatter implements ValueFormatter {

	@Override
	public String format(Object value, Operator op) {
		return String.valueOf(value);
	}

}
