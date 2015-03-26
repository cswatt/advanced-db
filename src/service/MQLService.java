package service;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.javanet.NetHttpTransport;

import java.io.IOException;

import result.MQLResult;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/**
 * @author Cecilia Watt (ciw2104)
 * @author Aikaterini Iliakopoulou (ai2315)
 * 
 * Makes a request to the Freebase MQL API based on a specific query
 * and creates an MQLResult object by parsing the retrieved json document.
 */
public class MQLService extends Service{
	private String query;
	private String apiKey;
	private MQLResult result;

	/**
	 * Creates an instance of MQLService
	 * @param apiKey
	 * @param query
	 */
	public MQLService(String apiKey, String query){
		this.query = query;
		this.apiKey = apiKey;
		requestInfo();
	}
	
	/**
	 * calls MQLBook and MQLOrganization, creates MQLResult
	 */
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
	
	/**
	 * Passes a formatted string that searches for authors who
	 * wrote a book with the query in the title
	 * @param init_query
	 * @return formatted string for MQL
	 * @throws IOException
	 * @throws ParseException
	 */
	public JSONArray MQLBook(String init_query) throws IOException, ParseException{
		String formatted_query = "[{\"/book/author/works_written\": [{\"a:name\": null,\"name~=\": \"" + init_query + "\"}],\"id\": null,\"name\": null,\"type\": \"/book/author\"}]";
		return MQL(formatted_query);
	}
	
	/**
	 * Passes a formatted string that searches for organization founders
	 * who created an organization with the query in the title
	 * @param init_query
	 * @return formatted string for MQL
	 * @throws IOException
	 * @throws ParseException
	 */
	public JSONArray MQLOrganization(String init_query) throws IOException, ParseException{
		String formatted_query = "[{\"/organization/organization_founder/organizations_founded\": [{\"a:name\": null,\"name~=\": \"" + init_query + "\"}],\"id\": null,\"name\": null,\"type\": \"/organization/organization_founder\"}]";
		return MQL(formatted_query);
	}
	
	/**
	 * Makes a request to the Freebase MQL API
	 * @param formatted_query
	 * @return JSONArray
	 * @throws IOException
	 * @throws ParseException
	 */
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
	
	/**
	 * Set the MQLResult
	 * @param result
	 */
	public void setResult(MQLResult result){
		this.result = result;
	}
	
	/**
	 * Return the MQLResult
	 * @return
	 */
	public MQLResult getResult(){
		return result;
	}
}
