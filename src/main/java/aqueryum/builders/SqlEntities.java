package aqueryum.builders;

import aqueryum.EntitiesFactory;
import aqueryum.recipients.SearchCriteria;



public class SqlEntities extends SqlRequestBuilder implements EntitiesVisitor {
	
	private final EntitiesFactory entitiesFactory; 

	public SqlEntities(EntitiesFactory f) {
		super(f);
        this.entitiesFactory = f;
    }

	@Override
	public String moreEntities(SearchCriteria criteria) {
		return criteria.moreEntities(this.entitiesFactory);
	}
	
	@Override
	public String moreFilters(SearchCriteria criteria, boolean thereAreMoreEntities) {
		if (thereAreMoreEntities)
			return criteria.moreFilters(this.entitiesFactory);
		else
			return this.visit(criteria); 
	}

}
