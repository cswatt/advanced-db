package creator;

import output.QueryBox;
import result.MQLResult;
import service.MQLService;

/**
 * @author Aikaterini Iliakopoulou (ai2315)
 * @author Cecilia Watt (ciw2104)
 * 
 * Class QueryBoxCreator performs the necessary actions given a query that
 * is of the form ("Who created [X]"). The given query has been already modified by 
 * the Retriever, so the method receives only the field [X] which passes on to MQLService 
 * object. The methods of MQLService call the Freebase MQL API, which searches all relations 
 * connected to [X]. Finally, it outputs the result through the QueryBox object. 
 */

public class QueryBoxCreator extends Creator{
	
	public QueryBoxCreator(String apiKey, String query){
		this.query = query;
		this.apiKey = apiKey;
	}
	
	/**
	 * Creates the output based on the retrieved result from MQL 
	 * Freebase API.
	 */
	public void create(){
		MQLService service = new MQLService(apiKey, query);
		MQLResult mqlresult = service.getResult();
		QueryBox queryBox = new QueryBox(mqlresult);
		queryBox.print();
	}
	
}
