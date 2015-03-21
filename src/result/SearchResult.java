package result;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.jayway.jsonpath.JsonPath;

public class SearchResult extends Result{
	
	private String mid;
	private String id;
	private String name;
	private String notable_id;
	
	private JSONObject search;
	
	public SearchResult(JSONObject search){
		this.search = search;
		parse();
		System.out.println(toString());
	}
	
	private void parse(){
		mid = JsonPath.read(search,"$.mid").toString();
		id = JsonPath.read(search,"$.id").toString();
		name = JsonPath.read(search,"$.name").toString();
		notable_id = JsonPath.read(search, "$.notable.id").toString();
	}
	private void setMid(String s){
		this.mid = s;
	}
	private String getMid(){
		return mid;
	}
	private void setId(String s){
		this.id = s;
	}
	private String getId(){
		return id;
	}
	private void setName(String s){
		this.name = s;
	}
	private String getName(){
		return name;
	}
	private void setNotableId(String s){
		this.notable_id = s;
	}
	private String getNotableId(){
		return notable_id;
	}
	//test
	public String toString(){
		String s = "mid: " + mid + "; id: " + id + "; name:" + name;
		return s;
	}
	
}
