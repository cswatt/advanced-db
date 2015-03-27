package service;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.jayway.jsonpath.JsonPath;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import result.SearchResult;
import result.TopicResult;
import result.Type;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;


/**
 * @author Cecilia Watt (ciw2104)
 * @author Aikaterini Iliakopoulou (ai2315)
 *
 * Makes a request to the Search API based on a specific query
 * and creates a SearchResult object by parsing the retrieved json document.
 */
public class SearchService extends Service{
	private String query;
	private String apiKey;
	private SearchResult result;
	private Set<Type> types = new HashSet<Type>();
	
	/**
	 * create an instance of SearchService
	 * @param apiKey
	 * @param query
	 */
	public SearchService(String apiKey, String query){
		this.query = query;
		this.apiKey = apiKey;
		types.add(Type.PERSON);
		types.add(Type.AUTHOR);
		types.add(Type.BUSINESSPERSON);
		types.add(Type.ACTOR);
		types.add(Type.LEAGUE);
		types.add(Type.TEAM);
	}
	
	/**
	 * Makes requests to Search API, creates TopicService objects
	 * for each MID retrieved, and looks at the resulting TopicResult
	 * objects to see if they match any of the entities we are looking for.
	 * This is done by taking the intersection of getTypes and the types set
	 * populated when this object was instantiated.
	 * 
	 * Fetches 20 of these results, then evaluates each of them.
	 * After each group of 5 results is evaluated, a status message is printed.
	 * 
	 * When a suitable result is found, a SearchResult object is created.
	 */
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
					System.out.println("5 results searched. Now looking at 10 results.");
					continue;
				}
				if (n==11){
					System.out.println("10 results searched. Now looking at 15 results.");
					continue;
				}
				if (n==16){
					System.out.println("15 results searched. Now looking at 20 results.");
					continue;
				}
				if (n==21){
					System.out.println("20 results searched, nothing found. Giving up.");
				}
			}
		} catch (Exception ex) {
			System.out.println("Sorry! I couldn't find anything!");
			return;
		} 
		if(found_result == null){
			System.out.println("Sorry! I couldn't find anything!");
			return;
		}
			
		SearchResult r = new SearchResult(found_result);
		setResult(r);
	}
	
	/**
	 * Makes a request to the Freebase Search API
	 * @param apiKey
	 * @param query   query
	 * @param limit   how many results to return
	 * @return        JSONArray of results
	 * @throws IOException
	 * @throws ParseException
	 */
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
	
	/**
	 * set the SearchResult
	 * @param result
	 */
	public void setResult(SearchResult result){
		this.result = result;
	}
	
	/**
	 * retrieve the SearchResult
	 * @return
	 */
	public SearchResult getResult(){
		return result;
	}
}
