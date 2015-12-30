package aqueryum;


public interface EntitiesFactory extends FieldFactory {

	String getEntity(String field);

	String getFilters(String field);  

}
