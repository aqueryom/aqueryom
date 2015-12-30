package aqueryum.builders;

import aqueryum.FieldFactory;
import aqueryum.recipients.SearchCriteria;

public abstract class SearchVisitor {
	
    protected final FieldFactory factory;	

    protected SearchVisitor(FieldFactory f) {
        this.factory = f;
    }
   
    public String visit(SearchCriteria c) {
        return this.visit(c, new StringBuilder(256)).toString();
    }

    public String visit(SearchCriteria c, StringBuilder b){
    	return this.visit(c, b, false).toString();	
    }
    
    public String visit(SearchCriteria c, boolean startsWithWhere){
    	return this.visit(c, new StringBuilder(256), startsWithWhere).toString();	
    }
    
    public abstract StringBuilder visit(SearchCriteria c, StringBuilder b, boolean startsWithWhere);
}
