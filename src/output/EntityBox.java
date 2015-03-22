package output;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Formatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import result.SearchResult;
import result.TopicResult;
import result.Type;
import service.SearchService;
import service.TopicService;
import entities.*;

public class EntityBox extends Output{
	private TopicResult result;
	private Set<Type> types;
	private Set<Type> persontypes = new HashSet<Type>();
	
	public EntityBox(TopicResult result){
		this.result = result;
		this.types = result.getTypes();
		persontypes.add(Type.ACTOR);
		persontypes.add(Type.AUTHOR);
		persontypes.add(Type.BUSINESSPERSON);
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
	
	public String persontypes(Person p){
		persontypes.retainAll(types);
		String s = persontypes.toString();
		s = "(" + s.substring(1, s.length()-1) + ")";
		return s;
	}
	
	public void printPerson(){
		Person p = result.getPerson();
		String name = p.getName() + " " + persontypes(p);
		String dateOfBirth = p.getDateOfBirth();
		String placeOfBirth = p.getPlaceOfBirth();
		
		String placeOfDeath = p.getPlaceOfDeath();
		String dateOfDeath = p.getDateOfDeath();
		String causeOfDeath = p.getCauseOfDeath();
		List<String> siblings = p.getSiblings();
		List<String> spouses = p.getSpouses();
		String description = p.getDescription();
		
		Formatter fmt = new Formatter();
		fmt.format(newline());
		fmt.format("|"); 
	    center("%s", fmt, name, 98); 
	    fmt.format("|\n");
	    fmt.format(newline());
	    leftalign(fmt, "Birthday:", dateOfBirth);
	    fmt.format(newline());
	    if (dateOfDeath!=null){
	    	leftalign(fmt, "Death:", dateOfDeath + " at " + placeOfDeath + ", cause: " + causeOfDeath);
	    	fmt.format(newline());
	    }
	    leftalign(fmt, "Place of birth:", placeOfBirth);
	    fmt.format(newline());
	    if (siblings != null && siblings.size() > 0) {
	    	leftalign(fmt, "Sibling(s):", siblings.get(0));
	    	if (siblings.size() > 1){
	    		for (String sibling : siblings.subList(1, siblings.size())){
	    			leftalign(fmt, "", sibling);
	    		}
	    	}
	    	fmt.format(newline());
	    }
	    if (spouses != null && spouses.size() > 0) {
	    	leftalign(fmt, "Spouse(s):", spouses.get(0));
	    	if (spouses.size() > 1){
	    		for (String spouse : spouses.subList(1, spouses.size())){
	    			leftalign(fmt, "", spouse);
	    		}
	    	}
	    	fmt.format(newline());
	    }
	    // TODO description
	    System.out.print(fmt); 
		
	}
	public void printBusinessPerson(){
		BusinessPerson b = result.getBusinessPerson();
		List<Organization> organizations_founded = b.getOrgsFounded();
		List<Organization> organizations_led = b.getOrgsLed();
		List<Organization> organizations_onboard = b.getOrgsOnboard();
	
		Formatter fmt = new Formatter();
		if (organizations_led != null && organizations_led.size() > 0) {
			leftalign(fmt, "Leadership:", "Organization", "Role", "Title", "From-To");
			for (Organization org : organizations_led){
				leftalign(fmt, "", snewline());
				leftalign(fmt, "", org.getName(), org.getLeaderRole(), org.getLeaderTitle(), org.getLeaderFrom() + " - " + org.getLeaderTo());
			}
	    	fmt.format(newline());
	    }
		if (organizations_onboard != null && organizations_onboard.size() > 0) {
			leftalign(fmt, "Leadership:", "Organization", "Role", "Title", "From-To");
			for (Organization org : organizations_onboard){
				leftalign(fmt, "",snewline());
				leftalign(fmt, "", org.getName(), org.getBoardMemberRole(), org.getBoardMemberTitle(), org.getBoardMemberFrom() + " - " + org.getBoardMemberTo());
			}
	    	fmt.format(newline());
	    }
		if (organizations_founded != null && organizations_founded.size() > 0) {
			leftalign(fmt, "Founded:", organizations_founded.get(0).getName());
	    	if (organizations_founded.size() > 1){
	    		for (Organization org : organizations_founded.subList(1, organizations_founded.size())){
	    			leftalign(fmt, "", org.getName());
	    		}
	    	}
	    	fmt.format(newline());
	    }
		
		System.out.print(fmt); 
	}	
	public void printAuthor(){
		Author a = result.getAuthor();
		List<String> books = a.getBooks();
		List<String> booksabout = a.getBooksAboutTheAuthor();
		List<String> influenced = a.getInfluenced();
		List<String> influencedby = a.getInfluencedBy();
		
		Formatter fmt = new Formatter();
		if (books != null && books.size() > 0) {
	    	leftalign(fmt, "Books:", books.get(0));
	    	if (books.size() > 1){
	    		for (String book : books.subList(1, books.size())){
	    			leftalign(fmt, "", book);
	    		}
	    	}
	    	fmt.format(newline());
	    }
		if (booksabout != null && booksabout.size() > 0) {
	    	leftalign(fmt, "Books about:", booksabout.get(0));
	    	if (booksabout.size() > 1){
	    		for (String book : booksabout.subList(1, booksabout.size())){
	    			leftalign(fmt, "", book);
	    		}
	    	}
	    	fmt.format(newline());
	    }
		if (influenced != null && influenced.size() > 0) {
			leftalign(fmt, "Influenced:", influenced.get(0));
	    	if (influenced.size() > 1){
	    		for (String person : influenced.subList(1, influenced.size())){
	    			leftalign(fmt, "", person);
	    		}
	    	}
	    	fmt.format(newline());
	    }
		if (influencedby != null && influencedby.size() > 0) {
			leftalign(fmt, "Influenced by:", influencedby.get(0));
	    	if (influencedby.size() > 1){
	    		for (String person : influencedby.subList(1, influencedby.size())){
	    			leftalign(fmt, "", person);
	    		}
	    	}
	    	fmt.format(newline());
	    }
		System.out.print(fmt); 
	}
	public void printActor(){
		Actor a = result.getActor();
		List<Film> films = a.getFilmography();
		
		Formatter fmt = new Formatter();
		if (films != null){
			Collections.sort(films);
			leftalign(fmt, "Films", "Character", "Film Name");
			leftalign(fmt, "", snewline());
			for (Film film : films){
				leftalign(fmt, "", film.getCharacter(), film.getName());
			}
		}
		System.out.print(fmt); 
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
	    	leftalign(fmt, "Sport:", sport);
	    	fmt.format(newline());
	    }
	    if (website != null){
	    	leftalign(fmt, "Official Website:", website);
	    	fmt.format(newline());
	    }
	    if (championship != null){
	    	leftalign(fmt, "Championship:", championship);
	    	fmt.format(newline());
	    }
	    if (slogan != null){
	    	leftalign(fmt, "Slogan:", slogan);
	    	fmt.format(newline());
	    }
	    // TODO description
	    if (teams != null && teams.size() > 0) {
	    	leftalign(fmt, "Teams:", teams.get(0).getName());
	    	if (teams.size() > 1){
	    		for (Team team : teams.subList(1, teams.size())){
	    			leftalign(fmt, "", team.getName());
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
	    center("%s", fmt, name, 98); 
	    fmt.format("|\n");
	    fmt.format(newline());
	    leftalign(fmt, "Name:", name);
    	fmt.format(newline());
	    if (sport != null){
	    	leftalign(fmt, "Sport:", sport);
	    	fmt.format(newline());
	    }
	    if (arena != null){
	    	leftalign(fmt, "Arena:", arena);
	    	fmt.format(newline());
	    }
	    if (championships != null && championships.size() > 0) {
	    	leftalign(fmt, "Championships:", championships.get(0));
	    	if (championships.size() > 1){
	    		for (String championship : championships.subList(1, championships.size())){
	    			leftalign(fmt, "", championship);
	    		}
	    	}
	    	fmt.format(newline());
	    }
	    if (founded != null){
	    	leftalign(fmt, "Founded:", founded);
	    	fmt.format(newline());
	    }
	    if (leagues != null && leagues.size() > 0) {
	    	leftalign(fmt, "Leagues:", leagues.get(0).getName());
	    	if (leagues.size() > 1){
	    		for (League league : leagues.subList(1, leagues.size())){
	    			leftalign(fmt, "", league.getName());
	    		}
	    	}
	    	fmt.format(newline());
	    }
	    if (locations != null && locations.size() > 0) {
	    	leftalign(fmt, "Locations:", locations.get(0));
	    	if (locations.size() > 1){
	    		for (String location : locations.subList(1, locations.size())){
	    			leftalign(fmt, "Locations:", location);
	    		}
	    	}
	    	fmt.format(newline());
	    }
	    if (coaches != null){
			//Collections.sort(films);
	    	leftalign(fmt, "Coaches:", "Name", "Position", "From/To");
			leftalign(fmt, "", snewline());
			for (Coach coach : coaches){
				String posString = listToString(coach.getPositions(), true);
				leftalign(fmt, "", coach.getName(), posString, coach.getFrom() + " / " + coach.getTo());
			}
			fmt.format(newline());
		}
	    if (players != null){
			//Collections.sort(films);
	    	leftalign(fmt, "Players:","Name", "Position", "Number", "From/To");
	    	leftalign(fmt, "", snewline());
			for (Player player : players){
				String posString = listToString(player.getPositions(), true);
				leftalign(fmt, "", player.getName(), posString, player.getNumber(), player.getFrom() + " / " + player.getTo());
			}
			fmt.format(newline());
		}
	    //TODO description
	    System.out.println(fmt); 
		
	}
	public static String newline(){
		return " -------------------------------------------------------------------------------------------------- \n";
	}
	public static String snewline(){
		return "-------------------------------------------------------------------------------";
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
	
	public static void leftalign(Formatter f, String label, String value){
		String fmtstr = "| %-17s%-80s|\n"; //97
		f.format(fmtstr, label, truncate(value, 80));
	}
	
	public static void leftalign(Formatter f, String label, String value1, String value2){
		String fmtstr = "| %-17s| %-38s| %-38s|\n"; //93
		f.format(fmtstr, label, truncate(value1, 38), truncate(value2, 38));
	}
	public static void leftalign(Formatter f, String label, String value1, String value2, String value3){
		String fmtstr = "| %-17s| %-25s| %-25s| %-24s|\n"; //91
		f.format(fmtstr, label, truncate(value1, 25), truncate(value2, 25), truncate(value3, 25));
	}
	public static void leftalign(Formatter f, String label, String value1, String value2, String value3, String value4){
		String fmtstr = "| %-17s| %-18s| %-18s| %-18s| %-18s|\n"; //89
		f.format(fmtstr, label, truncate(value1, 18), truncate(value2, 18), truncate(value3, 18), truncate(value4, 18));
	}
	
	public static String truncate(String s, int maxwidth){
		if (s == null) return "";
		if (s.length() < maxwidth){
			return s;
		}
		String trimmed = s.substring(0, maxwidth-3) + "...";
		return trimmed;
	}
	
	public static void main(String args[]){
		Formatter f = new Formatter();
		f.format(newline());
		leftalign(f, "Films", "Character", "Film Name");
		leftalign(f, "", snewline());
		leftalign(f, "", "Alice", "asdf");
		leftalign(f, "", "Bob", "asdf");
		f.format(newline());
		leftalign(f, "asdf", "asdf", "Film asdf", "asdf", "asdf");
		leftalign(f, "", snewline());
		leftalign(f, "", "Alice", "asdf", "asdf", "asdf");
		leftalign(f, "", "Bob", "asdf", "asdf", "asdf");
		f.format(newline());
		System.out.println(f); 
	}
	
}
