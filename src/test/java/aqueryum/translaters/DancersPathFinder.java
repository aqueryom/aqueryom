package aqueryum.translaters;

import java.util.HashSet;
import java.util.Set;

import aqueryum.PathFinder;
import aqueryum.PathFinderFactory;
import aqueryum.ValueFormatter;
//static final ValueFormatter FMT_SIMPLE 		= new SimpleFormatter();
//static final ValueFormatter FMT_DATE 			= new DateFormatter();
//static final ValueFormatter FMT_COLLECTION 	= new CollectionFormatter();
//static final ValueFormatter FMT_CHARSEQ 		= new CharSequenceFormatter();


public enum DancersPathFinder implements PathFinder {

	specialSign_type	("specialSign"	, "type"	, ", SpecialSign specialSign", ""),	
	specialSign_location("specialSign"	, "location", ", SpecialSign specialSign", " AND specialSign.owner = dancer.id"),	
	garter_color		("garter"		, "color"	, ", Garter garter"			, " AND garter.owner = dancer.id");	

    public final String alias;
    public final String column;
    public final boolean isKey;
    public final ValueFormatter type;
    private String join = "";
	private Set<String> entities = new HashSet<String>();

	private DancersPathFinder(String alias, String column, String entities, String join) {
		this(alias,  column, false, FMT_CHARSEQ);
		if (entities != null && !entities.equalsIgnoreCase(""))
			this.entities.add(entities); 
		this.join = join;
	}

	private DancersPathFinder(String alias, String column, boolean isKey, ValueFormatter type) {
		this.alias = alias;
		this.column = column;
		this.isKey = isKey;
		this.type = type;
	}

	@Override public ValueFormatter getValueFormatter() {		return type;	}

	@Override
	public String getAliasAndField() {
    	return this.alias + (this.isKey ? ".id." : ".") + this.column;
	}

	@Override
	public Set<String> getJoinEntities() { 
		return entities;
	}

	@Override
	public String getJoinFilters() {
		return join;
	}
	public static DancersPathFinder fromString(String name) {
		DancersPathFinder f = null;
        for (DancersPathFinder v : values()) {
            if (v.name().equalsIgnoreCase(name) || v.column.equalsIgnoreCase(name)) {
                f = v;
                break;
            }
        }
		return f;
	}
	
    public final static class Factory implements PathFinderFactory {

		@Override
		public PathFinder getPathFinder(String name) {
			DancersPathFinder f = DancersPathFinder.fromString(name);
            if (f == null) {
                throw new IllegalArgumentException(name);
            }
            return f;
		}
    }
}
