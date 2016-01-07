package aqueryum;



public interface QueryFactory {
	String filters		(PathFinderFactory factory);
	String joinEntities	(PathFinderFactory factory);
}
