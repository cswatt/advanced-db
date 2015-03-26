package service;

import org.json.simple.JSONObject;

import result.TopicResult;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.javanet.NetHttpTransport;

/**
 * @author Aikaterini Iliakopoulou (ai2315)
 * @author Cecilia Watt (ciw2104)
 * 
 * Makes request to the Freebase Topic API given a specific topic
 * and creates a TopicResult object by parsing the retrieved json document.
 */

public class TopicService extends Service{

	private String topicId;
	private String apiKey;
	
	private TopicResult result;
	
	/**
	 * Creates an instance of TopicService
	 * @param apiKey
	 * @param topicId
	 */
	public TopicService(String apiKey, String topicId){
		this.topicId = topicId;
		this.apiKey = apiKey;
	}
	
	/**
	 * Makes request to Freebase Topic API
	 */
	public void requestInfo(){
		try {
		      this.httpTransport = new NetHttpTransport();
		      this.requestFactory = httpTransport.createRequestFactory();

		      this.url = new GenericUrl("https://www.googleapis.com/freebase/v1/topic" + topicId);
		      url.put("key", apiKey);
		      
		      this.request = requestFactory.buildGetRequest(url);
		      this.httpResponse = request.execute();
		      
		      JSONObject topic = (JSONObject)parser.parse(httpResponse.parseAsString());
		     
		      this.result = new TopicResult(topic);
		      
		      this.result.parseTopic();
		
		    } catch (Exception ex) {
		      ex.printStackTrace();
		    }
	}
	
	/**
	 * Returns the TopicResult
	 * @return
	 */
	public TopicResult getResult(){
		return this.result;
	}
}
