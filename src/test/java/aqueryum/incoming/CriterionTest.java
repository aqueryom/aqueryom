package aqueryum.incoming;

import org.junit.Test;

import static org.junit.Assert.*;

import aqueryum.PathFinder;
import aqueryum.PathFinderFactory;
import aqueryum.ValueFormatter;
import aqueryum.incoming.Criterion;
import aqueryum.incoming.Operator;

public class CriterionTest {
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



	private final Criterion sut = new Criterion("dBegVali", OP, DBEGVALI);

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


	@Test
	public void testCondition() {
	   	String expected = CONDITION;
		String obtained = sut.condition(PATHFINDER).toString();     
		assertNotNull	("sut.condition(PATHFINDER) NULL", obtained);
		assertEquals	("sut.condition(PATHFINDER) KO", expected, obtained);
	}
}
