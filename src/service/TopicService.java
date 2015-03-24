package service;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import result.TopicResult;
import result.Type;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
/**
 * Makes request to the Freebase Topic API
 * creates a TopicResult object
 */
public class TopicService extends Service{

	private String topicId;
	private String apiKey;
	private String filter;
	
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
