package result;

import java.io.Serializable;

public interface JSONable extends Serializable {

	// Creates the JSON representation of the object that 
	// implements this interface
    public String toJSONString();
    
}