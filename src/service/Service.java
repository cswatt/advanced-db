package service;

import org.json.simple.parser.JSONParser;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;

/**
 * @author Cecilia Watt (ciw2104)
 * @author Aikaterini Iliakopoulou (ai2315)
 * 
 * The abstract class service is used for API requests to the Freebase API. 
 * Classes MQLService, SearchService and TopicService extend Service to make
 * requests to the MQL, Search and Topic API respectively.
 */

public abstract class Service {
	protected HttpTransport httpTransport;
	protected HttpRequestFactory requestFactory;
	protected HttpRequest request;
	protected HttpResponse httpResponse;
	
	protected GenericUrl url;
	
	protected JSONParser parser = new JSONParser();
	public abstract void requestInfo();
	
}
