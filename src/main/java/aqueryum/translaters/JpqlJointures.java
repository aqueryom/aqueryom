package aqueryum.translaters;

import java.util.HashSet;
import java.util.Set;

import aqueryum.Jointures;

public class JpqlJointures implements Jointures {

	private final Set<String> entities;
	private final Set<String> filters;  

	public JpqlJointures(Set<String> entities, Set<String> filters) {
		this.entities = entities;
		this.filters = filters;
	}

	public JpqlJointures() {
		this(new HashSet<String>(), new HashSet<String>());
	}

	@Override
	public Set<String> getEntities() {
		return entities;
	}

	@Override
	public Set<String> getFilters() {
		return filters;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((entities == null) ? 0 : entities.hashCode());
		result = prime * result + ((filters == null) ? 0 : filters.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JpqlJointures other = (JpqlJointures) obj;
		if (entities == null) {
			if (other.entities != null)
				return false;
		} else if (!entities.equals(other.entities))
			return false;
		if (filters == null) {
			if (other.filters != null)
				return false;
		} else if (!filters.equals(other.filters))
			return false;
		return true;
	}

}
