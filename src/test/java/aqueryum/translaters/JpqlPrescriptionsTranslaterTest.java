package aqueryum.translaters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import static aqueryum.incoming.Ordering.SortOrder.Ascending;
import aqueryum.PathFinder;
import aqueryum.PathFinderFactory;
import aqueryum.FilterFactory;
import aqueryum.ValueFormatter;
import aqueryum.incoming.Criterion;
import aqueryum.incoming.Operator;
import aqueryum.incoming.Ordering;
import aqueryum.incoming.Prescriptions;
import aqueryum.translaters.JpqlPrescriptionsTranslater;

public class JpqlPrescriptionsTranslaterTest {
	private static final String  		DBEGVALI 		= "2003-07-09";
	private static final Operator 		OP 				= Operator.le;
	private static final String 		CONDITION 		= "da.id.dBegVali <= to_date('2003-07-09', 'YYYY-MM-DD')";
	private static final String 		JOINTURE 		= " AND (da.id.cIntDa = outbnd.cIntDaSecCdt OR da.id.cIntDa = outbnd.cIntDaSecDbt)";
	private static final PathFinder 	PATHFINDER 		= new PathFinder() {
		HashSet<String> joinEntities = new HashSet<String>();
		@Override public ValueFormatter	getValueFormatter() 		{ return FMT_DATE; }
		@Override public String 		getAliasAndField() 			{ return "da.id.dBegVali"; }
		@Override public Set<String> 	getJoinEntities() 			{ joinEntities.add(", OutBound outbnd"); return joinEntities; }
		@Override public String 		getJoinFilters() 			{ return JOINTURE; }
	};
	private static final PathFinderFactory FACTORY 		= new PathFinderFactory() {
		@Override public PathFinder 	getPathFinder(String name) 	{ return PATHFINDER;	}
	};
	private static final FilterFactory 				QUERY_FACTORY 	= new Criterion("dBegVali", OP, DBEGVALI);
	private static final Collection<FilterFactory> 	ENSEMBLE 		= new ArrayList<FilterFactory>();
	private static final Prescriptions 				PRESCRIPTIONS 	= new Prescriptions();
	private static final Collection<Ordering> 		ORDERINGS 		= new ArrayList<Ordering>();
	private static final Ordering 					ORDERING 		= new Ordering("dBegVali"	, Ascending);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ENSEMBLE.add(QUERY_FACTORY); 
		ORDERINGS.add(ORDERING);
		PRESCRIPTIONS.setCriteria(ENSEMBLE); 
	}

	private final JpqlPrescriptionsTranslater sut = new JpqlPrescriptionsTranslater(FACTORY);

	@Test
	public void testtranslatePrescriptionsStringBuilderBoolean() {
	   	String expected = ", OutBound outbnd WHERE " + CONDITION + JOINTURE;
		String obtained = sut.translate(PRESCRIPTIONS, true);    
		assertNotNull	("sut.translate(PRESCRIPTIONS, true) NULL", obtained);
		assertEquals	("sut.translate(PRESCRIPTIONS, true) KO", expected, obtained);
	}

	@Test
	public void orderBy() {
	   	String expected 		= " ORDER BY da.id.dBegVali ASC";
		StringBuilder b 		= new StringBuilder();
		StringBuilder obtained 	= sut.translate(ORDERINGS, b);    
		assertNotNull			("sut.translate(ORDERINGS, b) NULL", obtained);
		assertEquals			("sut.translate(ORDERINGS, b) KO", expected, obtained.toString());
	}

}
