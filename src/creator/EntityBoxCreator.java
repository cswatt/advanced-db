package creator;

import output.EntityBox;
import result.TopicResult;
import service.SearchService;
import service.TopicService;

/**
 * @author Aikaterini Iliakopoulou (ai2315)
 * @author Cecilia Watt (ciw2104)
 * 
 * Class EntityBoxCreator performs the necessary actions given a query that
 * is an entity. First it calls methods of search service, which uses Freebase Search API 
 * to find the topic that corresponds to that particular entity. After, it calls 
 * methods of topic service, which uses Freebase Topic API to retrieve specific information
 * on a particular topic. Finally, it outpus the result through the EntityBox object. 
 * 
 */
public class EntityBoxCreator extends Creator{
	
	public EntityBoxCreator(String apiKey, String query){
		this.query = query;
		this.apiKey = apiKey;
	}
	
	/**
	 * Creates the output based on the retrieved results from both Search & Topic 
	 * Freebase APIs.
	 */
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
