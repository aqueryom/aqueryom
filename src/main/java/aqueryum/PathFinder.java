package aqueryum;

import java.util.Set;

import aqueryum.valueformatters.CharSequenceFormatter;
import aqueryum.valueformatters.CollectionFormatter;
import aqueryum.valueformatters.DateFormatter;
import aqueryum.valueformatters.SimpleFormatter;

public interface PathFinder
{
	static final ValueFormatter FMT_SIMPLE 		= new SimpleFormatter();
	static final ValueFormatter FMT_DATE 		= new DateFormatter();
	static final ValueFormatter FMT_COLLECTION 	= new CollectionFormatter();
	static final ValueFormatter FMT_CHARSEQ 	= new CharSequenceFormatter();
	/**
	 * determines the display of the value 
	 */
	ValueFormatter getValueFormatter();
	/**
	 * gives the path of the field 
	 */
	String getAliasAndField();
	Set<String> getJoinEntities();
	String getJoinFilters();
}
