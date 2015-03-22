package creator;

import output.QueryBox;
import result.MQLResult;
import service.MQLService;


public class QueryBoxCreator extends Creator{
	
	public QueryBoxCreator(String apiKey, String query){
		this.query = query;
		this.apiKey = apiKey;
	}
	
	public void create(){
		MQLService service = new MQLService(apiKey, query);
		MQLResult mqlresult = service.getResult();
		QueryBox queryBox = new QueryBox(mqlresult);
		queryBox.print();
	}
	
}
