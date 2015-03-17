package service;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.jayway.jsonpath.JsonPath;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

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
	
	//constructor - pass any arguments that might be necessary for the API call
	public SearchService(String apiKey, String query){
		this.query = query;
		this.apiKey = apiKey;
	}
	
	//implementation of the abstract method requestInfo with respect to the Search API 
	public void requestInfo(int limit){
		try {
			this.httpTransport = new NetHttpTransport();
			this.requestFactory = httpTransport.createRequestFactory();
			this.url = new GenericUrl("https://www.googleapis.com/freebase/v1/search");
			url.put("query", query);
			url.put("filter", "(any type:/people/person type:/book/author type:/film/actor type:/tv/tv_actor type:/organization/organization_founder type:/business/board_member type:/sports/sports_league type:/sports/sports_team type:/sports/professional_sports_team)");
			url.put("limit", limit);
			url.put("indent", "true");
			url.put("key", apiKey);
			this.request = requestFactory.buildGetRequest(url);
			this.httpResponse = request.execute();
			
			JSONObject response = (JSONObject)parser.parse(httpResponse.parseAsString());
			JSONArray results = (JSONArray)response.get("result");
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	//for testin' stuff
	public static void main(String args[]){
		String key = "AIzaSyDaVrp5DyCfmDx60NFbBBSzPCfK8X4qyho";
		Service service = new SearchService(key, "bill gates");
		service.requestInfo();
		/*JSONArray results = freebaseSearch("AIzaSyCKF0R67loGSSMHjazKxsZNgbvyRFOirmM", "bill gates",  5);
		if (results.isEmpty()){
			System.out.println("5 Search API result entries were considered. None of them of a supported type.");
			return;
		}
		String name = JsonPath.read(results.get(0),"$.name").toString();
		String mid = JsonPath.read(results.get(0),"$.mid").toString();
		System.out.println("found entity: " + name + ", " + mid);
		*/
	}
}
