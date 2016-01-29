package aqueryum.incoming;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import aqueryum.Jointures;
import aqueryum.PathFinder;
import aqueryum.PathFinderFactory;
import aqueryum.FilterFactory;
import aqueryum.ValueFormatter;
import aqueryum.incoming.Operator;
import aqueryum.translaters.JpqlJointures;

public class CriteriaTest {
	private static final String  		DBEGVALI 		= "2003-07-09";
	private static final Operator 		OP 				= Operator.le;
	private static final String 		CONDITION 		= "da.id.dBegVali <= to_date('2003-07-09', 'YYYY-MM-DD')";
	private static final Jointures 		JOINTURES 		= new JpqlJointures();//;
	private static final PathFinder 	PATHFINDER 		= new PathFinder() {
		@Override public ValueFormatter	getValueFormatter() 		{ return FMT_DATE; }
		@Override public String 		getAliasAndField() 			{ return "da.id.dBegVali"; }
		@Override public Jointures 		getJointures() 				{ 
			JOINTURES.getFilters().add(" AND (da.id.cIntDa = outbnd.cIntDaSecCdt OR da.id.cIntDa = outbnd.cIntDaSecDbt)"); 
			JOINTURES.getEntities().add(", OutBound outbnd");  
			return JOINTURES; 
		}
	};
	private static final PathFinderFactory FACTORY 		= new PathFinderFactory() {
		@Override public PathFinder 	getPathFinder(String name) 	{ return PATHFINDER;	}
	};
	private static final FilterFactory 				QUERY_FACTORY 	= new Criterion("dBegVali", OP, DBEGVALI);
	private static final Collection<FilterFactory> 	ENSEMBLE 		= new ArrayList<FilterFactory>();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ENSEMBLE.add(QUERY_FACTORY); 
	}
	private final Criteria sut = new Criteria(ENSEMBLE);

	@Test
	public void testFilters() {
		String expected = CONDITION;
		String obtained = sut.filters(FACTORY);   
		assertNotNull	("sut.filters(FACTORY) NULL", obtained);
		assertEquals	("sut.filters(FACTORY) KO", expected, obtained);
	}

	@Test
	public void testJoinFilters() {
	   	Set<String> expected = PATHFINDER.getJointures().getFilters();
		Set<String> obtained = sut.joinFilters(FACTORY);   
		assertNotNull	("sut.filters(FACTORY) NULL", obtained);
		assertEquals	("sut.filters(FACTORY) KO", expected, obtained);
	}

	@Test
	public void testJoinEntities() {
		Set<String> expected 	= PATHFINDER.getJointures().getEntities();
		Set<String> obtained 	= sut.joinEntities(FACTORY);    
		assertNotNull			("sut.joinEntities(FACTORY) NULL", obtained);
		assertEquals			("sut.joinEntities(FACTORY) KO", expected, obtained);
	}

}
