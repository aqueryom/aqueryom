package aqueryum.incoming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import static org.junit.Assert.*;

import aqueryum.FilterFactory;
import aqueryum.Jointures;
import aqueryum.PathFinder;
import aqueryum.PathFinderFactory;
import aqueryum.ValueFormatter;
import aqueryum.translaters.JpqlJointures;

public class PrescriptionsTest extends Prescriptions {
	
    private static final Set<String> ENTITIES 	= new HashSet<String>(Arrays.asList(", SpecialSign specialSign"));
	private static final Set<String> FILTERS 	= new HashSet<String>(Arrays.asList(" specialSign.owner = dancer.id"));
	private static final Jointures JOINTURES 	= new JpqlJointures(ENTITIES, FILTERS); 
	private static final PathFinderFactory FACTORY 		= new PathFinderFactory() {
		@Override public PathFinder 	getPathFinder(String name) 	{ 
			switch(name) {
			case "specialSign_type": {	return new PathFinder() {
				@Override public ValueFormatter getValueFormatter() { return FMT_CHARSEQ;		}
				@Override public String 		getAliasAndField() 	{ return "specialSign.type";}
				@Override public Jointures 		getJointures() 		{ return JOINTURES;			}
				}; }
			case "specialSign_location": {	return new PathFinder() { 
				@Override public ValueFormatter getValueFormatter() { return FMT_CHARSEQ;		}
				@Override public String 		getAliasAndField() 	{ return "specialSign.location";}
				@Override public Jointures 		getJointures() 		{ return JOINTURES;			}
				}; }
			}
			return null;	
			}
	};
	private static final Criterion FRECKLES_CRIT = new Criterion("specialSign_type"		, Operator.eq, "freckles");
	private static final Criterion CENSORED_CRIT = new Criterion("specialSign_location"	, Operator.eq, "censored");
	private  	   final Prescriptions BOTH 	= buildPrescriptions(FRECKLES_CRIT, CENSORED_CRIT);

	@Test
	public void testEntities() {
	   	String expected = ", SpecialSign specialSign"; 
		String obtained = BOTH.entities(FACTORY);    
		assertNotNull	("BOTH.entities(FACTORY) NULL", obtained);
		assertEquals	("BOTH.entities(FACTORY) KO", expected, obtained);
	}

	@Test
	public void testJoinFilters() {
	   	String expected = " specialSign.owner = dancer.id"; 
		String obtained = BOTH.joinFiltersToString(FACTORY);    
		assertNotNull	("BOTH.joinFilters(FACTORY) NULL", obtained);
		assertEquals	("BOTH.joinFilters(FACTORY) KO", expected, obtained);
	}

	@Test
	public void testFilters() {
	   	String expected = "specialSign.type = 'freckles' AND specialSign.location = 'censored'"; 
		String obtained = BOTH.filters(FACTORY);    
		assertNotNull	("BOTH.filters(FACTORY) NULL", obtained);
		assertEquals	("BOTH.filters(FACTORY) KO", expected, obtained);
	}

	protected Prescriptions buildPrescriptions(Criterion... criterions) {
		Collection<FilterFactory>  filterFactories = new ArrayList<FilterFactory>();
        for (Criterion criterion : criterions) {
    		filterFactories.add(criterion);
        }
 		return new Prescriptions(filterFactories, new ArrayList<Ordering>());
	}

}
