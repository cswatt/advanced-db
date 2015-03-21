package output;

import java.util.Arrays;
import java.util.Collections;
import java.util.Formatter;
import java.util.List;
import java.util.Set;

import result.TopicResult;
import result.Type;
import entities.*;

public class EntityBox extends Output{
	private TopicResult result;
	private Set<Type> types;
	
	public EntityBox(TopicResult result){
		this.result = result;
		this.types = result.getTypes();
	}
	
	public void print(){
		System.out.println("FORMATTED STUFF STARTS HERE");
		if(types.contains(Type.PERSON)){
			printPerson();
			if(types.contains(Type.BUSINESSPERSON)){
				printBusinessPerson();
			}
			if(types.contains(Type.AUTHOR)){
				printAuthor();
			}
			if(types.contains(Type.ACTOR)){
				printActor();
			}
		}
		else if(types.contains(Type.LEAGUE)){
			printLeague();
		}
		else if(types.contains(Type.TEAM)){
			printTeam();
		}
	}
	
	public void printPerson(){
		Person p = result.getPerson();
		String name = p.getName();
		String dateOfBirth = p.getDateOfBirth();
		String placeOfBirth = p.getPlaceOfBirth();
		
		String placeOfDeath = p.getPlaceOfDeath();
		String dateOfDeath = p.getDateOfDeath();
		String causeOfDeath = p.getCauseOfDeath();
		List<String> siblings = p.getSiblings();
		List<String> spouses = p.getSpouses();
		String description = p.getDescription();
		
		Formatter fmt = new Formatter();
		fmt.format("|"); 
	    center("%s", fmt, name, 100); 
	    fmt.format("|\n");
	    fmt.format(newline());
	    fmt.format("| %-20s %-80s|\n", "Birthday:", dateOfBirth);
	    fmt.format(newline());
	    if (dateOfDeath!=null){
	    	fmt.format("| %-20s %-80s|\n", "Death:", dateOfDeath + " at " + placeOfDeath + ", cause: " + causeOfDeath);
	    	fmt.format(newline());
	    }
	    fmt.format("| %-20s %-80s|\n", "Place of birth:", placeOfBirth);
	    fmt.format(newline());
	    if (siblings != null && siblings.size() > 0) {
	    	fmt.format("| %-20s %-80s|\n", "Sibling(s):", siblings.get(0));
	    	if (siblings.size() > 1){
	    		for (String sibling : siblings.subList(1, siblings.size())){
	    			fmt.format("| %-20s %-80s|\n", "", sibling);
	    		}
	    	}
	    	fmt.format(newline());
	    }
	    if (spouses != null && spouses.size() > 0) {
	    	fmt.format("| %-20s %-80s|\n", "Spouse(s):", spouses.get(0));
	    	if (spouses.size() > 1){
	    		for (String spouse : spouses.subList(1, spouses.size())){
	    			fmt.format("| %-20s %-80s|\n", "", spouse);
	    		}
	    	}
	    	fmt.format(newline());
	    }
	    // TODO description
	    System.out.println(fmt); 
		
	}
	public void printBusinessPerson(){
		BusinessPerson b = result.getBusinessPerson();
		if (b==null){
			System.out.println("huh.");
		}
		List<Organization> organizations_founded = b.getOrgsFounded();
		List<Organization> organizations_led = b.getOrgsLed();
		List<Organization> organizations_onboard = b.getOrgsOnboard();
	
		Formatter fmt = new Formatter();
		if (organizations_led != null && organizations_led.size() > 0) {
			fmt.format("| %-20s|%-20s|%-19s|%-19s|%-19s|\n", "Leadership:", "Organization", "Role", "Title", "From-To");
			for (Organization org : organizations_led){
				fmt.format("| %-20s %-80s|\n", "",snewline());
				fmt.format("| %-20s|%.20s|%.19s|%.19s|%.19s|\n", "", org.getName(), org.getLeaderRole(), org.getLeaderTitle(), org.getLeaderFrom() + " - " + org.getLeaderTo());
			}
	    	fmt.format(newline());
	    }
		if (organizations_onboard != null && organizations_onboard.size() > 0) {
			fmt.format("| %-20s|%-20s|%-19s|%-19s|%-19s|\n", "Leadership:", "Organization", "Role", "Title", "From-To");
			for (Organization org : organizations_onboard){
				fmt.format("| %-20s %-80s|\n", "",snewline());
				fmt.format("| %-20s|%.20s|%.19s|%.19s|%.19s|\n", "", org.getName(), org.getBoardMemberRole(), org.getBoardMemberTitle(), org.getBoardMemberFrom() + " - " + org.getBoardMemberTo());
			}
	    	fmt.format(newline());
	    }
		if (organizations_founded != null && organizations_founded.size() > 0) {
	    	fmt.format("| %-20s %-80s|\n", "Founded:", organizations_founded.get(0).getName());
	    	if (organizations_founded.size() > 1){
	    		for (Organization org : organizations_founded.subList(1, organizations_founded.size())){
	    			fmt.format("| %-20s %-80s|\n", "", org.getName());
	    		}
	    	}
	    	fmt.format(newline());
	    }
		
		System.out.println(fmt); 
	}	
	public void printAuthor(){
		Author a = result.getAuthor();
		List<String> books = a.getBooks();
		List<String> booksabout = a.getBooksAboutTheAuthor();
		List<String> influenced = a.getInfluenced();
		List<String> influencedby = a.getInfluencedBy();
		
		Formatter fmt = new Formatter();
		if (books != null && books.size() > 0) {
	    	fmt.format("| %-20s %-80s|\n", "Books:", books.get(0));
	    	if (books.size() > 1){
	    		for (String book : books.subList(1, books.size())){
	    			fmt.format("| %-20s %-80s|\n", "", book);
	    		}
	    	}
	    	fmt.format(newline());
	    }
		if (booksabout != null && booksabout.size() > 0) {
	    	fmt.format("| %-20s %-80s|\n", "Books about:", booksabout.get(0));
	    	if (booksabout.size() > 1){
	    		for (String book : booksabout.subList(1, booksabout.size())){
	    			fmt.format("| %-20s %-80s|\n", "", book);
	    		}
	    	}
	    	fmt.format(newline());
	    }
		if (influenced != null && influenced.size() > 0) {
	    	fmt.format("| %-20s %-80s|\n", "Influenced:", influenced.get(0));
	    	if (influenced.size() > 1){
	    		for (String person : influenced.subList(1, influenced.size())){
	    			fmt.format("| %-20s %-80s|\n", "", person);
	    		}
	    	}
	    	fmt.format(newline());
	    }
		if (influencedby != null && influencedby.size() > 0) {
	    	fmt.format("| %-20s %-80s|\n", "Influenced by:", influencedby.get(0));
	    	if (influencedby.size() > 1){
	    		for (String person : influencedby.subList(1, influencedby.size())){
	    			fmt.format("| %-20s %-80s|\n", "", person);
	    		}
	    	}
	    	fmt.format(newline());
	    }
		System.out.println(fmt); 
	}
	public void printActor(){
		Actor a = result.getActor();
		List<Film> films = a.getFilmography();
		
		Formatter fmt = new Formatter();
		if (films != null){
			Collections.sort(films);
			fmt.format("| %-20s %-40s %-40s|\n", "Films","Character", "Film Name");
			for (Film film : films){
				fmt.format("| %-20s %-40s %-40s|\n", "",film.getCharacter(), film.getName());
			}
		}
		System.out.println(fmt); 
	}
	public void printLeague(){
		League l = result.getLeague();
		String name = l.getName();
		String championship = l.getChampionship();
		String sport = l.getSport();
		String slogan = l.getSlogan();
		String website = l.getWebsite();
		String description = l.getDescription();
		List<Team> teams = l.getTeams();
		
		Formatter fmt = new Formatter();
		fmt.format(newline());
		fmt.format("|"); 
	    center("%s", fmt, name, 100); 
	    fmt.format("|\n");
	    fmt.format(newline());
	    if (sport != null){
	    	fmt.format("| %-20s %-80s|\n", "Sport:", sport);
	    	fmt.format(newline());
	    }
	    if (website != null){
	    	fmt.format("| %-20s %-80s|\n", "Official Website:", website);
	    	fmt.format(newline());
	    }
	    if (championship != null){
	    	fmt.format("| %-20s %-80s|\n", "Championship:", championship);
	    	fmt.format(newline());
	    }
	    if (slogan != null){
	    	fmt.format("| %-20s %-80s|\n", "Slogan:", slogan);
	    	fmt.format(newline());
	    }
	    // TODO description
	    if (teams != null && teams.size() > 0) {
	    	fmt.format("| %-20s %-80s|\n", "Books:", teams.get(0).getName());
	    	if (teams.size() > 1){
	    		for (Team team : teams.subList(1, teams.size())){
	    			fmt.format("| %-20s %-80s|\n", "", team.getName());
	    		}
	    	}
	    	fmt.format(newline());
	    }
		System.out.println(fmt); 
	    
	}
	public void printTeam(){
		Team t = result.getTeam();
		String name = t.getName();
		String description = t.getDescription();
		String sport = t.getSport();
		String arena = t.getArena();
		List<String> championships = t.getChampionships();
		List<Coach> coaches = t.getCoaches();
		String founded = t.getFoundedIn();
		List<League> leagues = t.getLeagues();
		List<String> locations = t.getLocations();
		List<Player> players = t.getPlayersRoster();
		
		Formatter fmt = new Formatter();
		fmt.format(newline());
		fmt.format("|"); 
	    center("%s", fmt, name, 100); 
	    fmt.format("|\n");
	    fmt.format(newline());
	    fmt.format("| %-20s %-80s|\n", "Name:", name);
    	fmt.format(newline());
	    if (sport != null){
	    	fmt.format("| %-20s %-80s|\n", "Sport:", sport);
	    	fmt.format(newline());
	    }
	    if (arena != null){
	    	fmt.format("| %-20s %-80s|\n", "Arena:", arena);
	    	fmt.format(newline());
	    }
	    if (championships != null && championships.size() > 0) {
	    	fmt.format("| %-20s %-80s|\n", "Championships:", championships.get(0));
	    	if (championships.size() > 1){
	    		for (String championship : championships.subList(1, championships.size())){
	    			fmt.format("| %-20s %-80s|\n", "", championship);
	    		}
	    	}
	    	fmt.format(newline());
	    }
	    if (founded != null){
	    	fmt.format("| %-20s %-80s|\n", "Founded:", founded);
	    	fmt.format(newline());
	    }
	    if (leagues != null && leagues.size() > 0) {
	    	fmt.format("| %-20s %-80s|\n", "Leagues:", leagues.get(0).getName());
	    	if (leagues.size() > 1){
	    		for (League league : leagues.subList(1, leagues.size())){
	    			fmt.format("| %-20s %-80s|\n", "", league.getName());
	    		}
	    	}
	    	fmt.format(newline());
	    }
	    if (locations != null && locations.size() > 0) {
	    	fmt.format("| %-20s %-80s|\n", "Locations:", locations.get(0));
	    	if (locations.size() > 1){
	    		for (String location : locations.subList(1, locations.size())){
	    			fmt.format("| %-20s %-80s|\n", "", location);
	    		}
	    	}
	    	fmt.format(newline());
	    }
	    if (coaches != null){
			//Collections.sort(films);
			fmt.format("| %-20s |%-25s|%-25s|%-25s|\n", "Coaches","Name", "Position", "From/To");
			fmt.format("| %-20s %-80s|\n", "",snewline());
			for (Coach coach : coaches){
				String posString = "";
				for (String p : coach.getPositions()){
					posString = posString + p + ", ";
				}
				fmt.format("| %-20s |%-25s|%-25s|%-25s|\n", "",coach.getName(),posString, coach.getFrom() + " / " + coach.getTo());
			}
			fmt.format(newline());
		}
	    if (players != null){
			//Collections.sort(films);
			fmt.format("| %-20s |%-25s|%-25s|%-25s|%-25s|\n", "Players","Name", "Position", "Number", "From/To");
			fmt.format("| %-20s %-80s|\n", "",snewline());
			for (Player player : players){
				String posString = "";
				for (String p : player.getPositions()){
					posString = posString + p + ", ";
				}
				fmt.format("| %-20s |%-25s|%-25s|%-25s|%-25s|\n", "",player.getName(),posString, player.getNumber(), player.getFrom() + " / " + player.getTo());
			}
			fmt.format(newline());
		}
	    //TODO description
	    System.out.println(fmt); 
		
	}
	public String newline(){
		return " -------------------------------------------------------------------------------------------------- \n";
	}
	public String snewline(){
		return "----------------------------------------------------------------------------------";
	}
	public void center(String fmtStr, Formatter f, Object obj, int width){
		String str; 
		Formatter tmp = new Formatter(); 
		tmp.format(fmtStr, obj); 
		str = tmp.toString(); 
		int dif = width - str.length(); 
		if(dif < 0) { 
		 f.format(str); 
		 return; 
		} 
		char[] pad = new char[dif/2]; 
		Arrays.fill(pad, ' ');  
		f.format(new String(pad)); 
		 
		f.format(str); 
		
		pad = new char[width-dif/2-str.length()]; 
		Arrays.fill(pad, ' ');  
		f.format(new String(pad)); 
	}
	
}
