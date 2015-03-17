package result;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.jsonpath.JsonPath;

public class TopicResult extends Result{
	static Gson gson = new GsonBuilder()
    .excludeFieldsWithoutExposeAnnotation()
    .create();
	
	private JSONObject topic;
	
	private List<Type> types = new ArrayList<Type>();
	
	
	public TopicResult(JSONObject topic){
		this.topic = topic;
		
		parseTopic();
	}
	
	private void parseTopic(){
		String res = JsonPath.read(topic,"$.property['/type/object/type']").toString();
		System.out.println("res: "+res);
		
		Values values = gson.fromJson(res, Values.class);
		values.printValues();
	}
}
