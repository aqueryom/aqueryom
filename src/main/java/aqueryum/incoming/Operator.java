package aqueryum.incoming;


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
