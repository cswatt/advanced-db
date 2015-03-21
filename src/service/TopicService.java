package service;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import result.TopicResult;

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
		     
		      //System.out.println(JsonPath.read(topic,"$.property['/type/object/name'].values[0].value").toString());
		      result = new TopicResult(topic);
		      
		      result.parseTopic();
		      /*
		      if(result.getPerson() != null)
		    	  result.getPerson().print();
		      if(result.getBusinessPerson() != null)
		    	  result.getBusinessPerson().print();
		      if(result.getAuthor() != null)
		    	  result.getAuthor().print();
		      if(result.getActor() != null)
		    	  result.getActor().print();
		      if(result.getLeague() != null)
		    	  result.getLeague().print();
		      if(result.getTeam() != null)
		    	  result.getTeam().print();
		      */
		      
		    } catch (Exception ex) {
		      ex.printStackTrace();
		    }
	}
	public TopicResult getResult(){
		return result;
	}
	//temporary for testing purposes
	public static void main(String[] args) {
		String key = "AIzaSyDaVrp5DyCfmDx60NFbBBSzPCfK8X4qyho";
		
		Service service = new TopicService(key,"/m/0jm3v");
		service.requestInfo();
	}

}
