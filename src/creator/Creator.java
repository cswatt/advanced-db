package creator;

/**
 * @author Aikaterini Iliakopoulou (ai2315)
 * @author Cecilia Watt (ciw2104)
 * 
 * Class Creator is the abstract class that is extended by both
 * EntityBoxCreator and QueryBoxCreator and defines the creation of the
 * answer based on the type of query the user gives to the system.
 * 
 */

public abstract class Creator {
	String query;
	String apiKey;
	
	/**
	 * Class for creating the output of the retrieval process
	 */
	public void create(){
		
	}
}
