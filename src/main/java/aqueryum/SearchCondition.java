package aqueryum;



public interface SearchCondition {
	
	String visit(FieldFactory factory);
	String moreEntities(EntitiesFactory factory);
	String moreFilters (EntitiesFactory factory);  
}
