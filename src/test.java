import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.jayway.jsonpath.JsonPath;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Test {
	public static void main(String[] args) throws IOException, ParseException {
		JSONArray results = freebaseSearch("AIzaSyCKF0R67loGSSMHjazKxsZNgbvyRFOirmM", "bill gates",  5);
		if (results.isEmpty()){
			System.out.println("5 Search API result entries were considered. None of them of a supported type.");
			return;
		}
		String mid = JsonPath.read(results.get(0),"$.mid").toString();
		System.out.println(mid);
	}

	public static JSONArray freebaseSearch(String key, String query, int limit) throws IOException, ParseException{
		HttpTransport httpTransport = new NetHttpTransport();
		HttpRequestFactory requestFactory = httpTransport.createRequestFactory();
		JSONParser parser = new JSONParser();
		GenericUrl url = new GenericUrl("https://www.googleapis.com/freebase/v1/search");
		url.put("query", query);
		url.put("filter", "(any type:/people/person type:/book/author type:/film/actor type:/tv/tv_actor type:/organization/organization_founder type:/business/board_member type:/sports/sports_league type:/sports/sports_team type:/sports/professional_sports_team)");
		url.put("limit", limit);
		url.put("indent", "true");
		url.put("key", key);
		HttpRequest request = requestFactory.buildGetRequest(url);
		HttpResponse httpResponse = request.execute();
		JSONObject response = (JSONObject)parser.parse(httpResponse.parseAsString());
		JSONArray results = (JSONArray)response.get("result");
		return results;
	}
}