package aqueryum;

import java.util.Set;



public interface FilterFactory {
	String 			filters		(PathFinderFactory factory);
	Set<String> joinEntities	(PathFinderFactory factory);
	Set<String> joinFilters		(PathFinderFactory factory); 
}
