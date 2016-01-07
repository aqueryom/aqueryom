package aqueryum.incoming;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import aqueryum.PathFinder;
import aqueryum.PathFinderFactory;
import aqueryum.QueryFactory;
import aqueryum.ValueFormatter;
import aqueryum.incoming.Operator;

public class CriteriaTest {
	private static final String  		DBEGVALI 		= "2003-07-09";
	private static final Operator 		OP 				= Operator.le;
	private static final String 		CONDITION 		= "da.id.dBegVali <= to_date('2003-07-09', 'YYYY-MM-DD')";
	private static final String 		JOINTURE 		= " AND (da.id.cIntDa = outbnd.cIntDaSecCdt OR da.id.cIntDa = outbnd.cIntDaSecDbt)";
	private static final PathFinder 	PATHFINDER 		= new PathFinder() {
		@Override public ValueFormatter	getValueFormatter() 		{ return FMT_DATE; }
		@Override public String 		getAliasAndField() 			{ return "da.id.dBegVali"; }
		@Override public String 		getJoinEntities() 			{ return ""; }
		@Override public String 		getJoinFilters() 			{ return JOINTURE; }
	};
	private static final PathFinderFactory FACTORY 		= new PathFinderFactory() {
		@Override public PathFinder 	getPathFinder(String name) 	{ return PATHFINDER;	}
	};
	private static final QueryFactory 				QUERY_FACTORY 	= new Criterion("dBegVali", OP, DBEGVALI);
	private static final Collection<QueryFactory> 	ENSEMBLE 		= new ArrayList<QueryFactory>();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ENSEMBLE.add(QUERY_FACTORY); 
	}
	private final Criteria sut = new Criteria(ENSEMBLE);

	@Test
	public void testFilters() {
	   	String expected = CONDITION + JOINTURE;
		String obtained = sut.filters(FACTORY);   
		assertNotNull	("sut.filters(FACTORY) NULL", obtained);
		assertEquals	("sut.filters(FACTORY) KO", expected, obtained);
	}

	@Test
	public void testJoinEntities() {
	   	String expected = "";
		String obtained = sut.joinEntities(FACTORY);    
		assertNotNull	("sut.joinEntities(FACTORY) NULL", obtained);
		assertEquals	("sut.joinEntities(FACTORY) KO", expected, obtained);
	}

}
