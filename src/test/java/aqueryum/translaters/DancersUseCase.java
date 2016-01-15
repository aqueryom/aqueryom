package aqueryum.translaters;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import static org.junit.Assert.*;

import aqueryum.FilterFactory;
import aqueryum.incoming.Criterion;
import aqueryum.incoming.Operator;
import aqueryum.incoming.Ordering;
import aqueryum.incoming.Prescriptions;

public class DancersUseCase {
	private static final Collection<Ordering> ORDER_BY_AGE = new ArrayList<Ordering>(); 

	private static final String CONDITIONS_LULU = "SELECT dancer.name FROM Dancer dancer, Garter garter"
												+ " WHERE garter.color = 'ROSE'"
												+ " AND garter.owner = dancer.id";
	private static final FilterFactory 				GARTER_COLOR_ROSE 		= new Criterion("garter_color", Operator.eq, "ROSE");

	private static final String CONDITIONS_PAM  = "SELECT dancer.name FROM Dancer dancer, SpecialSign specialSign"
												+ " WHERE specialSign.type = 'BEAUTY_SPOT'"
												+ " AND specialSign.location = 'CENSORED'"
												+ " AND specialSign.owner = dancer.id"
												;

	private static final FilterFactory 				HAS_A_BEAUTY_SPOT 		= new Criterion("specialSign_type"		, Operator.eq, "BEAUTY_SPOT");
	private static final FilterFactory 				IN_A_SECRET_PLACE 		= new Criterion("specialSign_location"	, Operator.eq, "CENSORED");

	private final JpqlPrescriptionsTranslater 	sut 					= new JpqlPrescriptionsTranslater(new DancersPathFinder.Factory());
	private final Collection<FilterFactory> 	filterFactories 		= new ArrayList<FilterFactory>();

	@Test
	public void shouldFindLuluLaNantaise() {
		filterFactories.add(GARTER_COLOR_ROSE);
		Prescriptions PRESCRIPTIONS_LULU 	= new Prescriptions(filterFactories, ORDER_BY_AGE);
	   	String expected = CONDITIONS_LULU; 
		String obtained = "SELECT dancer.name FROM Dancer dancer" 
						+ sut.translate(PRESCRIPTIONS_LULU, true);    
		assertNotNull	("sut.translate(PRESCRIPTIONS_LULU) NULL", obtained);
		assertEquals	("sut.translate(PRESCRIPTIONS_LULU) KO", expected, obtained);
	}

	@Test
	public void shouldFindPamelaTchou() {
		filterFactories.add(HAS_A_BEAUTY_SPOT);
		filterFactories.add(IN_A_SECRET_PLACE);
		Prescriptions PRESCRIPTIONS_PAM 	= new Prescriptions(filterFactories, ORDER_BY_AGE);
	   	String expected = CONDITIONS_PAM; 
		String obtained = "SELECT dancer.name FROM Dancer dancer" 
						+ sut.translate(PRESCRIPTIONS_PAM, true);    
		assertNotNull	("sut.translate(PRESCRIPTIONS_PAM) NULL", obtained);
		assertEquals	("sut.translate(PRESCRIPTIONS_PAM) KO", expected, obtained);
	}

}
