package service;

import org.json.simple.parser.JSONParser;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;

public abstract class Service {
	protected HttpTransport httpTransport;
	protected HttpRequestFactory requestFactory;
	protected HttpRequest request;
	protected HttpResponse httpResponse;
	
	protected GenericUrl url;
	
	protected JSONParser parser = new JSONParser();
	public abstract void requestInfo();
	
}
