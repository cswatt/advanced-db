package result;

import java.io.Serializable;

/**
 * @author Aikaterini Iliakopoulou (ai2315)
 * @author Cecilia Watt (ciw2104)
 * 
 * Interface JSONable is used for the mapping of values in the system.
 * It uses specific annotation to map certain fields to certain values. 
 */
public interface JSONable extends Serializable {

	// Creates the JSON representation of the object that 
	// implements this interface
    public String toJSONString();
    
}