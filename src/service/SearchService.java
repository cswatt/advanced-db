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

public class SearchService extends Service{
	private String query;
	private String apiKey;
	
	private HttpTransport httpTransport;
	private HttpRequestFactory requestFactory;
	private HttpRequest request;
	private HttpResponse httpResponse;
	
	private GenericUrl url;
	JSONParser parser = new JSONParser();
	
	private HashSet<String> types = new HashSet<String>();
	
	public SearchService(String apiKey, String query){
		this.query = query;
		this.apiKey = apiKey;
		
		types.add("/people/person");
		types.add("/book/author");
		types.add("/film/actor");
		types.add("/tv/tv_actor");
		types.add("/organization/organization_founder");
		types.add("/business/board_member");
		types.add("/sports/sports_league");
		types.add("/sports/sports_team");
		types.add("/sports/professional_sports_team");
	}
	
	public void requestInfo(){
		String mid = "";
		String notable_id = "";
		
		JSONObject found_result = null;
		
		try {
			boolean found = false;
			int limit = 5;
			while(!found && limit <= 20){
				JSONArray results = search(this.apiKey, this.query,  limit);
				for (Object result : results){
					mid = JsonPath.read(result,"$.mid").toString();
					try{notable_id = JsonPath.read(result, "$.notable.id").toString();}
					catch (Exception e){notable_id = "";}
					if (types.contains(notable_id)){
						found_result = (JSONObject)result;
						found = true;
						//System.out.println("found");
						break;
					}
				}
				if (!found) System.out.println(limit + " Search API result entries were considered. None of them of a supported type.");
				
				if (limit == 20) System.out.println("nothing found");
				
				limit = limit + 5;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		Result res = new SearchResult(found_result);
	}
	

	public JSONArray search(String apiKey, String query, int limit) throws IOException, ParseException{
		JSONArray results = null;
		this.httpTransport = new NetHttpTransport();
		this.requestFactory = httpTransport.createRequestFactory();
		this.url = new GenericUrl("https://www.googleapis.com/freebase/v1/search");
		url.put("query", query);
		url.put("limit", limit);
		url.put("indent", "true");
		url.put("key", apiKey);
		this.request = requestFactory.buildGetRequest(url);
		this.httpResponse = request.execute();
		
		JSONObject response = (JSONObject)parser.parse(httpResponse.parseAsString());
		results = (JSONArray)response.get("result");
		return results;
	}
	
	//for testin' stuff
	public static void main(String args[]){
		String key = "AIzaSyDaVrp5DyCfmDx60NFbBBSzPCfK8X4qyho";
		Service service = new SearchService(key, "bill gates");
		service.requestInfo();
	}
}
