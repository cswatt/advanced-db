package result;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.jayway.jsonpath.JsonPath;
/**
 * Represents a result from the SearchService
 * Makes mid, id, name, and notable_id accessible,
 * though largely only mid is used.
 */
public class SearchResult extends Result{
	
	private String mid;
	private String id;
	private String name;
	private String notable_id;
	
	private JSONObject search;
	
	/**
	 * Creates a SearchResult object, calls parse
	 * @param search
	 */
	public SearchResult(JSONObject search){
		this.search = search;
		parse();
	}
	
	/**
	 * Extracts values from JSON
	 */
	private void parse(){
		mid = JsonPath.read(search,"$.mid").toString();
		id = JsonPath.read(search,"$.id").toString();
		name = JsonPath.read(search,"$.name").toString();
		notable_id = JsonPath.read(search, "$.notable.id").toString();
	}
	
	/**
	 * Methods to get/set the mid, id, name, notable_id
	 */
	public void setMid(String s){
		this.mid = s;
	}
	public String getMid(){
		return mid;
	}
	public void setId(String s){
		this.id = s;
	}
	public String getId(){
		return id;
	}
	public void setName(String s){
		this.name = s;
	}
	public String getName(){
		return name;
	}
	public void setNotableId(String s){
		this.notable_id = s;
	}
	public String getNotableId(){
		return notable_id;
	}
	
}
