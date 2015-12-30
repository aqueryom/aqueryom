package aqueryum;

public interface SearchField
{
	/**
	 * determines the display of the value in SqlRequestBuilder
	 */
	Class<?> getType();
	/**
	 * gives the form of the field in SqlRequestBuilder
	 */
	String getAliasAndField();
}
