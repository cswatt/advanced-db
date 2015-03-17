package service;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.jayway.jsonpath.JsonPath;

import java.io.IOException;
import java.util.HashSet;

import result.Result;
import result.SearchResult;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MQLService extends Service{
	private String query;
	private String apiKey;
	
	private HttpTransport httpTransport;
	private HttpRequestFactory requestFactory;
	private HttpRequest request;
	private HttpResponse httpResponse;
	
	private GenericUrl url;
	JSONParser parser = new JSONParser();

	public MQLService(String apiKey, String query){
		this.query = query;
		this.apiKey = apiKey;
	}
	public void requestInfo(){
		try{
	      this.httpTransport = new NetHttpTransport();
	      this.requestFactory = httpTransport.createRequestFactory();
	      this.url = new GenericUrl("https://www.googleapis.com/freebase/v1/mqlread");
	      url.put("query", query);
	      url.put("key", apiKey);
	      this.request = requestFactory.buildGetRequest(url);
	      this.httpResponse = request.execute();
	      
	      JSONObject response = (JSONObject)parser.parse(httpResponse.parseAsString());
	      JSONArray results = (JSONArray)response.get("result");
	      for (Object result : results) {
	        System.out.println(JsonPath.read(result,"$.name").toString());
	      }
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
	}
	public static void main(String args[]){
		String key = "AIzaSyDaVrp5DyCfmDx60NFbBBSzPCfK8X4qyho";
		Service service = new MQLService(key, "[{\"id\":null,\"name\":null,\"type\":\"/astronomy/planet\"}]");
		service.requestInfo();
	}
}
