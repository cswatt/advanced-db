package result;
import org.json.simple.JSONArray;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.jayway.jsonpath.JsonPath;


/**
 * @author Cecilia Watt (ciw2104)
 * @author Aikaterini Iliakopoulou (ai2315)
 * 
 * Class MQLResult represents the results of an MQL search.
 */

public class MQLResult{
	private Map<Role, List<String>> mql_map = new HashMap<Role, List<String>>();
	private JSONArray book_results;
	private JSONArray organization_results;
	
	/**
	 * Creates an MQLResult object and calls parse()
	 * @param book_results
	 * @param organization_results
	 */
	public MQLResult(JSONArray book_results, JSONArray organization_results){
		this.book_results = book_results;
		this.organization_results = organization_results;
		parse();
	}
	
	/**
	 * Sorts through the two JSONArrays. Extracts the person's name and
	 * creates a Role object with their name and job (author or businessperson)
	 * 
	 * Consolidates all these matchings of Role and the thing they created into mql_map
	 */
	private void parse(){
		for (Object res : book_results){
			String author = JsonPath.read(res,"$.name").toString();
			Role role = new Role(author, "Author");
			List<String> books = JsonPath.read(res,"$./book/author/works_written[*].a:name");
			mql_map.put(role, books);
		}
		
		for (Object res : organization_results){
			String businessperson = JsonPath.read(res,"$.name").toString();
			Role role = new Role(businessperson, "Businessperson");
			List<String> organizations = JsonPath.read(res,"$./organization/organization_founder/organizations_founded[*].a:name");
			mql_map.put(role, organizations);
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
	/**
	 * return the map MQLMap
	 * @return
	 */
	public Map getMQLMap(){
		return this.mql_map;
	}
}
