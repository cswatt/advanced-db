package creator;

import output.EntityBox;
import result.TopicResult;
import service.SearchService;
import service.TopicService;

public class EntityBoxCreator extends Creator{
	
	public EntityBoxCreator(String apiKey, String query){
		this.query = query;
		this.apiKey = apiKey;
	}
	
	public void create(){
		SearchService sservice = new SearchService(apiKey, query);
		sservice.requestInfo();
		String mId = sservice.getResult().getMid();

		TopicService tservice = new TopicService(apiKey,mId);
		tservice.requestInfo();
		TopicResult result = tservice.getResult();
		
		EntityBox entityBox = new EntityBox(result);
		entityBox.print();
	}
}
