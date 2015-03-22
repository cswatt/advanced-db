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

public class TopicService extends Service{

	private String topicId;
	private String apiKey;
	private String filter;
	
	private TopicResult result;
	
	public TopicService(String apiKey, String topicId){
		this.topicId = topicId;
		this.apiKey = apiKey;
	}
	
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
	public TopicResult getResult(){
		return this.result;
	}
	//temporary for testing purposes
	public static void main(String[] args) {
		String key = "AIzaSyDaVrp5DyCfmDx60NFbBBSzPCfK8X4qyho";
		
		TopicService service = new TopicService(key,"/m/081k8");
		service.requestInfo();
		
	      
	      if(service.getResult().getPerson() != null)
	    	  service.getResult().getPerson().print();
	      if(service.getResult().getBusinessPerson() != null)
	    	  service.getResult().getBusinessPerson().print();
	      if(service.getResult().getAuthor() != null)
	    	  service.getResult().getAuthor().print();
	      if(service.getResult().getActor() != null)
	    	  service.getResult().getActor().print();
	      if(service.getResult().getLeague() != null)
	    	  service.getResult().getLeague().print();
	      if(service.getResult().getTeam() != null)
	    	  service.getResult().getTeam().print();
	      
	}

}
