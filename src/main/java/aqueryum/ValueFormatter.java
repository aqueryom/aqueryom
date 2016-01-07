package aqueryum;

import aqueryum.incoming.Operator;

public interface ValueFormatter {
	String format(Object value, Operator op);
}
