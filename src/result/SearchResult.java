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
	
	//test
	public String toString(){
		String s = "mid: " + mid + "; id: " + id + "; name:" + name;
		return s;
	}
	
}
