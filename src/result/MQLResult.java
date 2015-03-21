package result;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import output.QueryBox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jayway.jsonpath.JsonPath;


public class MQLResult extends Result{
	private Map<List<String>, List<String>> mql_map = new HashMap<List<String>, List<String>>();
	private JSONArray book_results;
	private JSONArray organization_results;
	
	public MQLResult(JSONArray book_results, JSONArray organization_results){
		this.book_results = book_results;
		this.organization_results = organization_results;
		parse();
	}
	
	private void parse(){
		for (Object res : book_results){
			String author = JsonPath.read(res,"$.name").toString();
			List<String> author_tuple = Arrays.asList(author, "Author");
			List<String> books = JsonPath.read(res,"$./book/author/works_written[*].a:name");
			mql_map.put(author_tuple, books);
		}
		
		for (Object res : organization_results){
			String businessperson = JsonPath.read(res,"$.name").toString();
			List<String> businessperson_tuple = Arrays.asList(businessperson, "Businessperson");
			List<String> organizations = JsonPath.read(res,"$./organization/organization_founder/organizations_founded[*].a:name");
			mql_map.put(businessperson_tuple, organizations);
		}
	}
	
	public void setBookResults(JSONArray book_results){
		this.book_results = book_results;
	}
	public JSONArray getBookResults(){
		return this.book_results;
	}
	public void setOrganizationResults(JSONArray organization_results){
		this.organization_results= organization_results;
	}
	public JSONArray getOrganizationResults(){
		return this.organization_results;
	}
	public void setMQLMap(Map m){
		this.mql_map = m;
	}
	public Map getMQLMap(){
		return this.mql_map;
	}
}
