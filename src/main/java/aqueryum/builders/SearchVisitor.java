package aqueryum.builders;

import aqueryum.PathFinderFactory;
import aqueryum.incoming.Prescriptions;

public abstract class SearchVisitor {
	
    protected final PathFinderFactory factory;	

    protected SearchVisitor(PathFinderFactory f) {
        this.factory = f;
    }
   
    public String visit(Prescriptions c) {
        return this.visit(c, new StringBuilder(256)).toString();
    }

    public String visit(Prescriptions c, StringBuilder b){
    	return this.visit(c, b, false).toString();	
    }
    
    public String visit(Prescriptions c, boolean startsWithWhere){
    	return this.visit(c, new StringBuilder(256), startsWithWhere).toString();	
    }
    
    public abstract StringBuilder visit(Prescriptions c, StringBuilder b, boolean startsWithWhere);
}
