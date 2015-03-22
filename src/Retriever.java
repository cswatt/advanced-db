import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import creator.EntityBoxCreator;
import creator.QueryBoxCreator;

import output.EntityBox;

import result.SearchResult;
import result.TopicResult;
import result.Type;
import service.SearchService;
import service.TopicService;


public class Retriever {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		System.out.println("Hi! I am your very intelligent entity & query searcher!");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println();
		System.out.print("Please provide your API key to start searching: ");
		String apiKey = null;
		try {
			apiKey = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(apiKey == null){
			System.err.println("I am sorry, this is not a valid API key");
			return;
		}
		
		while(true){
			System.out.println();
			System.out.print("What would you like me to search for? ");
			String query = null;
			try {
				query = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(query == null){
				System.err.println("I am sorry, this is not a valid query - Please try again");
				continue;
			}
			
			//decide whether query is entity or mql
			String[] qtokens = query.split("\\s+");
			
			if(qtokens[0].toLowerCase().equals("who") && qtokens[1].toLowerCase().equals("created")){
				query = query.replace(qtokens[0], "");
				query = query.replace(qtokens[1], "");
				query = query.replace("?", "");
			
				QueryBoxCreator qcreator = new QueryBoxCreator(apiKey,query);
				
				qcreator.create();
			}
			else{
				EntityBoxCreator ecreator = new EntityBoxCreator(apiKey,query);
				
				ecreator.create();
			}
		}

	}

}
