package result;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MQLResult extends Result{
	private static final long serialVersionUID = -7934442049449016087L;
	private Map<String, List<String>> a = new HashMap<String, List<String>>();
	private JSONObject mql;
	
	public MQLResult(JSONObject mql){
		this.mql = mql;
		parse();
	}
	
	private void parse(){
		//some stuff here
	}
}
