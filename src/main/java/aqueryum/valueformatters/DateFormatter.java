package aqueryum.valueformatters;

import aqueryum.ValueFormatter;
import aqueryum.incoming.Operator;

public class DateFormatter implements ValueFormatter {

	@Override
	public String format(Object value, Operator op) {
		return "to_date('" + value + "', 'YYYY-MM-DD')";
	}

}
