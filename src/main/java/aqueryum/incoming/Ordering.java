package aqueryum.incoming;

import aqueryum.PathFinderFactory;
import aqueryum.PathFinder;

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

    public String visit(PathFinderFactory factory) {
        PathFinder 		pathFinder 	= factory.getPathFinder(this.field);
    	StringBuilder 	result 		= new StringBuilder(pathFinder.getAliasAndField());
        return result.append(' ')
  	          		 .append(this.order)
  	          		 .toString();
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
    public String toString(){
    	return "{'field': "+ this.field +", 'order': "+this.order+"}";
    }
    
}