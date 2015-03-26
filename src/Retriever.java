import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import creator.EntityBoxCreator;
import creator.QueryBoxCreator;


/**
 * @author Aikaterini Iliakopoulou (ai2315)
 * @author Cecilia Watt (ciw2104)
 * 
 * Class Retriever is responsible for getting the initial input from the user and call 
 * afterwards the appropriate methods to do the searching, based on the type of input.
 * If the user input is an entity, then the Retriever calls the EntityBoxCreator otherwise
 * if the user input is a query ("Who created [X]"), then after modifying the query, the Retriever
 * calls the QueryBoxCreator.
 */

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
				if(query.contains("?"))
					query = query.replaceAll("?", "");
			
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
