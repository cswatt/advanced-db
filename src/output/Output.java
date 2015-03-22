package output;

import java.util.List;

public abstract class Output {
	public abstract void print();
	
	public String listToString(List<String> list){
		String finalstring = "";
		if (list.size() == 1){
			finalstring = list.get(0);
			return finalstring;
		}
		if (list.size() == 2){
			finalstring = list.get(0) + " and " + list.get(1);
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
