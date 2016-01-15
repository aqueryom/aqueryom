package aqueryum.translaters;

import aqueryum.PathFinderFactory;
import aqueryum.incoming.Prescriptions;

public abstract class AbstractPrescriptionsTranslater {
	
    protected final PathFinderFactory factory;	

    protected AbstractPrescriptionsTranslater(PathFinderFactory f) {
        this.factory = f;
    }
   
    public String translate(Prescriptions prescriptions) { 
        return this.translate(prescriptions, new StringBuilder(256)).toString();
    }

    public String translate(Prescriptions prescriptions, StringBuilder b){
    	return this.translate(prescriptions, b, false).toString();	
    }
    
    public String translate(Prescriptions prescriptions, boolean startsWithWhere){
    	return this.translate(prescriptions, new StringBuilder(256), startsWithWhere).toString();	
    }
    
    public abstract StringBuilder translate(Prescriptions prescriptions, StringBuilder b, boolean startsWithWhere);
}
