package output;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import result.MQLResult;
import result.Role;

/**
 * 
 * @author Cecilia Watt (ciw2104)
 * @author Aikaterini Iliakopoulou (ai2315)
 * 
 * Given an MQLResult object, prints a list of formatted results
 */

public class QueryBox extends Output{
	private MQLResult result;
	private Map<Role, List<String>> mql_map;
	private List<Role> role_list = new ArrayList<Role>();
	//private Map<String, List<String>> final_map = new HashMap<String, List<String>>();
	
	/**
	 * instantiate QueryBox
	 * @param result MQLResult
	 */
	public QueryBox(MQLResult result){
		this.result = result;
		mql_map = result.getMQLMap();
		sort(mql_map);
	}
	
	/**
	 * put the keys in a list and alphabetize
	 * @param mql_map
	 */
	public void sort(Map mql_map){
		Set<Role> keys = mql_map.keySet();
		for (Role r : keys){
			role_list.add(r);
		}
		Collections.sort(role_list);
	}
	
	/**
	 * add angle brackets to each list item
	 * @param list
	 * @return
	 */
	public List<String> angleBracket(List<String> list){
		List<String> bracketed = new ArrayList<String>();
		for (String s : list){
			bracketed.add("<" + s + ">");
		}
		return bracketed;
	}
	
	/**
	 * actually print
	 */
	public void print(){
		int i = 1;
		for (Role r : role_list){
			List<String> titles = angleBracket(mql_map.get(r));
			String titlestring = listToString(titles, false);
			System.out.print(i + ". " + r.toString() + " created " + titlestring);			
			System.out.print("\n");
			i++;
		}
	}
}
