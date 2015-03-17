package result;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.jsonpath.JsonPath;

public class TopicResult extends Result{
	static Gson gson = new GsonBuilder()
    .excludeFieldsWithoutExposeAnnotation()
    .create();
	
	private JSONObject topic;
	
	private Set<Type> types = new HashSet<Type>();
	
	
	public TopicResult(JSONObject topic){
		this.topic = topic;
		
	}
	
	public void parseTopic(){
		//find types first
		String res = JsonPath.read(topic,"$.property['/type/object/type']").toString();
		Values values = gson.fromJson(res, Values.class);
		for(Value value : values.getValues()){
			if(value.getId().equals("/people/person")){
				types.add(Type.PERSON);
			}
			if(value.getId().equals("/book/author")){
				types.add(Type.AUTHOR);
			}
			if(value.getId().equals("/organization/organization_founder")){
				types.add(Type.ORGANIZATION_FOUNDER);
			}
			if(value.getId().equals("/business/board_member")){
				types.add(Type.BUSINESSPERSON);
			}
			if(value.getId().equals("/tv/tv_actor")){
				types.add(Type.ACTOR);
			}
			if(value.getId().equals("/film/actor")){
				types.add(Type.ACTOR);
			}
			if(value.getId().equals("/sports/sports_league")){
				types.add(Type.LEAGUE);
			}
			if(value.getId().equals("/sports/sports_team")){
				types.add(Type.TEAM);
			}
			if(value.getId().equals("/sports/professional_sports_team")){
				types.add(Type.TEAM);
			}
		}
	}
}
