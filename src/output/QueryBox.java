package output;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import result.MQLResult;

public class QueryBox extends Output{
	private MQLResult result;
	private Map<String, List<String>> final_map = new HashMap<String, List<String>>();
	
	public QueryBox(MQLResult result){
		this.result = result;
		Map<List<String>, List<String>> mql_map = result.getMQLMap();
		sort(mql_map);
	}
	
	public void sort(Map mql_map){
		Set<List<String>> keys = mql_map.keySet();
		for (List<String> key : keys) {
			String s = key.get(0) + " (as " + key.get(1) + ")";
			List<String> l = angleBracket((List<String>) mql_map.get(key));
			final_map.put(s, l);
		}
	}
	
	public List<String> angleBracket(List<String> list){
		List<String> bracketed = new ArrayList<String>();
		for (String s : list){
			bracketed.add("<" + s + ">");
		}
		return bracketed;
	}
	
	public void print(){
		List<String> sorted_names = new ArrayList<String>();
		sorted_names.addAll(final_map.keySet());
		Collections.sort(sorted_names);
		int i = 1;
		for (String name : sorted_names){
			List<String> titles = final_map.get(name);
			String titlestring = listToString(titles);
			System.out.print(i + ". " + name + " created " + titlestring);			
			System.out.print("\n");
			i++;
		}
	}
}
