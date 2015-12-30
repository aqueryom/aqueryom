package aqueryum.builders;

import aqueryum.recipients.SearchCriteria;

public interface EntitiesVisitor {

	String moreEntities(SearchCriteria criteria);

	String moreFilters(SearchCriteria criteria, boolean thereAreMoreEntities); 

}
 