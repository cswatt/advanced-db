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
import java.util.Set;

import result.Result;
import result.SearchResult;
import result.TopicResult;
import result.Type;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import output.EntityBox;

public class SearchService extends Service{
	private String query;
	private String apiKey;
	private SearchResult result;
	private Set<Type> types = new HashSet<Type>();
	//private HashSet<String> types = new HashSet<String>();
	
	public SearchService(String apiKey, String query){
		this.query = query;
		this.apiKey = apiKey;
		types.add(Type.PERSON);
		types.add(Type.AUTHOR);
		types.add(Type.ORGANIZATION_FOUNDER);
		types.add(Type.BUSINESSPERSON);
		types.add(Type.ACTOR);
		types.add(Type.LEAGUE);
		types.add(Type.TEAM);
	}
	
	public void requestInfo(){
		String mid = "";
		
		JSONObject found_result = null;
		
		try {
			int n = 1;
			JSONArray results = search(this.apiKey, this.query,  20);
			for (Object result : results){
				mid = JsonPath.read(result,"$.mid").toString();
				TopicService ts = new TopicService(apiKey, mid);
				ts.requestInfo();
				TopicResult tr = ts.getResult();
				Set<Type> temp_types = tr.getTypes();
				Set<Type> intersection = new HashSet<Type>(types);
				intersection.retainAll(temp_types);
				if (!intersection.isEmpty()){
					found_result = (JSONObject)result;
					break;
				}
				n++;
				if (n==6){
					System.out.println("searching next");
					continue;
				}
				if (n==11){
					System.out.println("searching next");
					continue;
				}
				if (n==16){
					System.out.println("searching next");
					continue;
				}
				if (n==21){
					System.out.println("giving up");
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		SearchResult r = new SearchResult(found_result);
		setResult(r);
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
	
	public void setResult(SearchResult result){
		this.result = result;
	}
	
	public SearchResult getResult(){
		return result;
	}
	
	public static void main(String args[]){
		String key = "AIzaSyDaVrp5DyCfmDx60NFbBBSzPCfK8X4qyho";
		SearchService service = new SearchService(key, "william shakespeare");
		service.requestInfo();
		SearchResult sr = service.getResult();
		String str = sr.getMid();
		
		TopicService t = new TopicService(key, str);
		t.requestInfo();
		TopicResult tr = t.getResult();
		EntityBox e = new EntityBox(tr);
		e.print();
	}
}
