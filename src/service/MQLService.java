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

import result.MQLResult;
import result.Result;
import result.SearchResult;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import output.QueryBox;

public class MQLService extends Service{
	private String query;
	private String apiKey;
	private MQLResult result;

	public MQLService(String apiKey, String query){
		this.query = query;
		this.apiKey = apiKey;
		requestInfo();
	}
	
	public void requestInfo(){
		try {
			JSONArray book_results = MQLBook(query);
			JSONArray organization_results = MQLOrganization(query);
			MQLResult r = new MQLResult(book_results, organization_results);
			setResult(r);
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
	}
	
	public JSONArray MQLBook(String init_query) throws IOException, ParseException{
		String formatted_query = "[{\"/book/author/works_written\": [{\"a:name\": null,\"name~=\": \"" + init_query + "\"}],\"id\": null,\"name\": null,\"type\": \"/book/author\"}]";
		return MQL(formatted_query);
	}
	
	public JSONArray MQLOrganization(String init_query) throws IOException, ParseException{
		String formatted_query = "[{\"/organization/organization_founder/organizations_founded\": [{\"a:name\": null,\"name~=\": \"" + init_query + "\"}],\"id\": null,\"name\": null,\"type\": \"/organization/organization_founder\"}]";
		return MQL(formatted_query);
	}
	
	public JSONArray MQL(String formatted_query) throws IOException, ParseException{
      this.httpTransport = new NetHttpTransport();
      this.requestFactory = httpTransport.createRequestFactory();
      this.url = new GenericUrl("https://www.googleapis.com/freebase/v1/mqlread");
      url.put("query", formatted_query);
      url.put("key", apiKey);
      this.request = requestFactory.buildGetRequest(url);
      this.httpResponse = request.execute();
      
      JSONObject response = (JSONObject)parser.parse(httpResponse.parseAsString());
      JSONArray results = (JSONArray)response.get("result");
      return results;
	}
	
	public void setResult(MQLResult result){
		this.result = result;
	}
	
	public MQLResult getResult(){
		return result;
	}
	
	public static void main(String args[]){
		String key = "AIzaSyDaVrp5DyCfmDx60NFbBBSzPCfK8X4qyho";
		MQLService service = new MQLService(key, "Microsoft");
		MQLResult r = service.getResult();
		QueryBox q = new QueryBox(r);
		q.print();
	}
}
