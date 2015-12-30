package aqueryum.recipients;

import aqueryum.FieldFactory;
import aqueryum.SearchField;

public class Ordering {
	
	private String field;
    private SortOrder order;
    
    public enum SortOrder {
        Ascending("ASC"), Descending("DESC");
        private String od;
        private SortOrder(String od) {
            this.od = od;
        }
        public String getOd() {
            return this.od;
        }
        @Override
        public String toString() {
        	return this.od;
        }
    }

    public Ordering() {
    }
    
    public Ordering(String field, SortOrder order) {
        this.field = field;
        this.order = order;
    }
    
    public String getField() {
    	return this.field;
    }
    
    public void setField(String f) {
    	this.field = f;
    }
    
    public SortOrder getOrder() {
    	return this.order;
    }
    
    public void setOrder(SortOrder o) {
    	this.order = o;
    }

    public String visit(FieldFactory factory) {
    	StringBuilder result = new StringBuilder();
        SearchField f = factory.getField(this.field);
        result.append(f.getAliasAndField())
	          .append(' ')
	          .append(this.order);
        return result.toString();
    }
    
    public String toString(){
    	return "{'field': "+ this.field +", 'order': "+this.order+"}";
    }
    
}