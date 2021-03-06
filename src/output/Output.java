package output;

import java.util.List;

/**
 * 
 * @author Cecilia Watt (ciw2104)
 * @author Aikaterini Iliakopoulou (ai2315)
 * 
 * The abstract class that is responsible for the system's output. 
 * This class is extended by EntityBox and QueryBox, which respectively print
 * the entity or query result based on the parsing of the json document returned by
 * the corresponding APIs. 
 */

public abstract class Output {
	public abstract void print();
	
	public String listToString(List<String> list, boolean simple){
		String finalstring = "";
		if (list.size() == 1){
			finalstring = list.get(0);
			return finalstring;
		}
		if (list.size() == 2 && !simple){
			finalstring = list.get(0) + " and " + list.get(1);
			return finalstring;
		}
		if (list.size() == 2 && simple){
			finalstring = list.get(0) + ", " + list.get(1);
			return finalstring;
		}
		if (list.size() >= 3){
			for(String s : list.subList(0, list.size() - 2)){
				finalstring = finalstring + s + ", ";
			}
			finalstring = finalstring + "and " + list.get(list.size()-1);
			return finalstring;
		}
		return finalstring;
	}
}
