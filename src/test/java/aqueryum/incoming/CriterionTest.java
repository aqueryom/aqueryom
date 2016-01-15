package aqueryum.incoming;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import static org.junit.Assert.*;

import aqueryum.Jointures;
import aqueryum.PathFinder;
import aqueryum.PathFinderFactory;
import aqueryum.ValueFormatter;
import aqueryum.incoming.Criterion;
import aqueryum.incoming.Operator;
import aqueryum.translaters.JpqlJointures;

public class CriterionTest {
	private static final String  		DBEGVALI 		= "2003-07-09";
	private static final Operator 		OP 				= Operator.le;
	private static final String 		CONDITION 		= "da.id.dBegVali <= to_date('2003-07-09', 'YYYY-MM-DD')";
	private static final String 		JOINTURE 		= " AND (da.id.cIntDa = outbnd.cIntDaSecCdt OR da.id.cIntDa = outbnd.cIntDaSecDbt)";
	private static final Jointures 		JOINTURES 		= new JpqlJointures();
	private static final PathFinder 	PATHFINDER 		= new PathFinder() {
		@Override public ValueFormatter	getValueFormatter() 		{ return FMT_DATE; }
		@Override public String 		getAliasAndField() 			{ return "da.id.dBegVali"; }
		@Override public Jointures 		getJointures() 				{ 
			JOINTURES.getFilters().add(JOINTURE); 
//			JOINTURES.getEntities().add(", OutBound outbnd");  
			return JOINTURES; 
		}
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
		Set<String> expected 	= new HashSet<String>();
		Set<String> obtained 	= sut.joinEntities(FACTORY);    
		assertNotNull			("sut.joinEntities(FACTORY) NULL", obtained);
		assertEquals			("sut.joinEntities(FACTORY) KO", expected, obtained);
	}


	@Test
	public void testCondition() {
	   	String expected = CONDITION;
		String obtained = sut.condition(PATHFINDER).toString();     
		assertNotNull	("sut.condition(PATHFINDER) NULL", obtained);
		assertEquals	("sut.condition(PATHFINDER) KO", expected, obtained);
	}
}
