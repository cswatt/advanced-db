package output;

import java.util.Collections;
import java.util.Formatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import result.TopicResult;
import result.Type;
import entities.*;


/**
 * 
 * @author Cecilia Watt (ciw2104)
 * @author Aikaterini Iliakopoulou (ai2315)
 * 
 * Given a TopicResult object,
 * prints out the formatted information
 */
public class EntityBox extends Output{
	private TopicResult result;
	private Set<Type> types;
	private Set<Type> persontypes = new HashSet<Type>();
	
	/**
	 * instantiate an entitybox
	 * @param result TopicResult
	 */
	public EntityBox(TopicResult result){
		this.result = result;
		this.types = result.getTypes();
		persontypes.add(Type.ACTOR);
		persontypes.add(Type.AUTHOR);
		persontypes.add(Type.BUSINESSPERSON);
	}
	
	/**
	 * Prints relevant information depending on the type
	 */
	public void print(){
		if(types.contains(Type.PERSON)){
			printPerson();
			if(types.contains(Type.BUSINESSPERSON)) printBusinessPerson();
			if(types.contains(Type.AUTHOR)) printAuthor();			
			if(types.contains(Type.ACTOR)) printActor();	
		}
		else if(types.contains(Type.LEAGUE)) printLeague();
		else if(types.contains(Type.TEAM)) printTeam();
	}
	
	/**
	 * get what the "person" types are and format a string
	 * that has the types separated by comma, inside parentheses
	 * @param p Person
	 * @return formatted string
	 */
	public String persontypes(Person p){
		persontypes.retainAll(types);
		String s = persontypes.toString();
		s = "(" + s.substring(1, s.length()-1) + ")";
		
		if (!types.contains(Type.BUSINESSPERSON) && !types.contains(Type.AUTHOR) && !types.contains(Type.ACTOR)){
			s = "(PERSON)";
		}
		return s;
	}
	
	/**
	 * print the Person fields
	 */
	public void printPerson(){
		Person p = result.getPerson();
		
		String name = p.getName() + " " + persontypes(p);
		String dateOfBirth = p.getDateOfBirth();
		String placeOfBirth = p.getPlaceOfBirth();
		String placeOfDeath = p.getPlaceOfDeath();
		String dateOfDeath = p.getDateOfDeath();
		String causeOfDeath = p.getCauseOfDeath();
		List<String> siblings = p.getSiblings();
		List<Spouse> spouses = p.getSpouses();
		String description = p.getDescription();
		
		Formatter fmt = new Formatter();
		
		// print centered headline
		fmt.format(newline());
		center(fmt, name);
	    fmt.format(newline());
	    
	    // print birthday
	    if(dateOfBirth!=null){
	    	leftalign(fmt, "Birthday:", dateOfBirth);
	    	fmt.format(newline());
	    }
	    
	    // print death stuff
	    if (dateOfDeath!=null){
	    	String deathinfo = dateOfDeath;
	    	if (placeOfDeath != null) deathinfo = deathinfo + " at " + placeOfDeath;
	    	if (causeOfDeath != null) deathinfo = deathinfo + ", cause: " + causeOfDeath;
	    	leftalign(fmt, "Death:", deathinfo);
	    	fmt.format(newline());
	    }
	    
	    // print place of birth
	    if (placeOfBirth!=null){
	    	leftalign(fmt, "Place of birth:", placeOfBirth);
	    	fmt.format(newline());
	    }
	    
	    // print siblings
	    if (siblings != null && siblings.size() > 0) {
	    	leftalign(fmt, "Sibling(s):", siblings.get(0));
	    	if (siblings.size() > 1){
	    		for (String sibling : siblings.subList(1, siblings.size())){
	    			leftalign(fmt, "", sibling);
	    		}
	    	}
	    	fmt.format(newline());
	    }
	    
	    // print spouses
	    if (spouses != null && spouses.size() > 0) {
	    	leftalign(fmt, "Spouse(s):", spouses.get(0).getName());
	    	if (spouses.size() > 1){
	    		for (Spouse spouse : spouses.subList(1, spouses.size())){
	    			String spouseinfo = spouse.getName();
	    			if ((spouse.getFrom()!= null) || (spouse.getTo() != null)){
	    				if (spouse.getFrom() == null) spouseinfo = spouseinfo + " ( - " + spouse.getTo()+")";
	    				if (spouse.getTo() == null) spouseinfo = spouseinfo + " (" + spouse.getFrom() + ")";
	    				if ((spouse.getFrom() != null) && (spouse.getTo() != null)) spouseinfo = spouseinfo + " (" + spouse.getFrom() + " - " + spouse.getTo() + ")";
	    			}
	    			if (spouse.getMarriageLocation() != null) spouseinfo = spouseinfo + " @ " + spouse.getMarriageLocation();
	    			leftalign(fmt, "", spouseinfo);
	    		}
	    	}
	    	fmt.format(newline());
	    }
	    
	    // print description
	    if (description != null){
	    	wrap(fmt, "Description", description);
	    	fmt.format(newline());
	    }
	    System.out.print(fmt);
	}
	
	/**
	 * print businessperson fields
	 */
	public void printBusinessPerson(){
		BusinessPerson b = result.getBusinessPerson();
		
		List<Organization> organizations_founded = b.getOrgsFounded();
		List<Organization> organizations_led = b.getOrgsLed();
		List<Organization> organizations_onboard = b.getOrgsOnboard();
	
		Formatter fmt = new Formatter();
		
		// print orgs led
		if (organizations_led != null && organizations_led.size() > 0) {
			leftalign(fmt, "Leadership:", "Organization", "Role", "Title", "From-To");
			for (Organization org : organizations_led){
				leftalign(fmt, "", snewline());
				leftalign(fmt, "", org.getName(), org.getLeaderRole(), org.getLeaderTitle(), org.getLeaderFrom() + " - " + org.getLeaderTo());
			}
	    	fmt.format(newline());
	    }
		
		// print orgs onboard
		if (organizations_onboard != null && organizations_onboard.size() > 0) {
			leftalign(fmt, "Board member:", "Organization", "Role", "Title", "From-To");
			for (Organization org : organizations_onboard){
				leftalign(fmt, "",snewline());
				leftalign(fmt, "", org.getName(), org.getBoardMemberRole(), org.getBoardMemberTitle(), org.getBoardMemberFrom() + " - " + org.getBoardMemberTo());
			}
	    	fmt.format(newline());
	    }
		
		// print orgs founded
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
	
	/**
	 * print author fields
	 */
	public void printAuthor(){
		Author a = result.getAuthor();
		
		List<String> books = a.getBooks();
		List<String> booksabout = a.getBooksAboutTheAuthor();
		List<String> influenced = a.getInfluenced();
		List<String> influencedby = a.getInfluencedBy();
		
		Formatter fmt = new Formatter();
		
		// print books
		if (books != null && books.size() > 0) {
	    	leftalign(fmt, "Books:", books.get(0));
	    	if (books.size() > 1){
	    		for (String book : books.subList(1, books.size())){
	    			leftalign(fmt, "", book);
	    		}
	    	}
	    	fmt.format(newline());
	    }
		
		// print books about
		if (booksabout != null && booksabout.size() > 0) {
	    	leftalign(fmt, "Books about:", booksabout.get(0));
	    	if (booksabout.size() > 1){
	    		for (String book : booksabout.subList(1, booksabout.size())){
	    			leftalign(fmt, "", book);
	    		}
	    	}
	    	fmt.format(newline());
	    }
		
		// print influences
		if (influenced != null && influenced.size() > 0) {
			leftalign(fmt, "Influenced:", influenced.get(0));
	    	if (influenced.size() > 1){
	    		for (String person : influenced.subList(1, influenced.size())){
	    			leftalign(fmt, "", person);
	    		}
	    	}
	    	fmt.format(newline());
	    }
		
		// print influenced by
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
	
	/**
	 * print actor fields
	 */
	public void printActor(){
		Actor a = result.getActor();
		
		List<Film> films = a.getFilmography();
		
		Formatter fmt = new Formatter();
		
		// print films
		if (films != null && films.size() > 0){
			Collections.sort(films);
			leftalign(fmt, "Films:", "Character", "Film Name");
			leftalign(fmt, "", snewline());
			for (Film film : films){
				leftalign(fmt, "", film.getCharacter(), film.getName());
			}
			fmt.format(newline());
		}
		System.out.print(fmt); 
	}
	
	/**
	 * print league fields
	 */
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
		
		// print header
		fmt.format(newline());
		center(fmt, name);
	    fmt.format(newline());
	    
	    // print sport
	    if (sport != null){
	    	leftalign(fmt, "Sport:", sport);
	    	fmt.format(newline());
	    }
	    
	    // print website
	    if (website != null){
	    	leftalign(fmt, "Official Website:", website);
	    	fmt.format(newline());
	    }
	    
	    // print championship
	    if (championship != null){
	    	leftalign(fmt, "Championship:", championship);
	    	fmt.format(newline());
	    }
	    
	    // print slogan
	    if (slogan != null){
	    	leftalign(fmt, "Slogan:", slogan);
	    	fmt.format(newline());
	    }
	    
	    // print description
	    if (description != null){
	    	wrap(fmt, "Description", description);
	    	fmt.format(newline());
	    }
	    
	    // print teams
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
	
	/**
	 * print team fields
	 */
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
		
		// print header
		fmt.format(newline());
	    center(fmt, name); 
	    fmt.format(newline());
	    
	    // print name
	    leftalign(fmt, "Name:", name);
    	fmt.format(newline());
    	
    	// print sport
	    if (sport != null){
	    	leftalign(fmt, "Sport:", sport);
	    	fmt.format(newline());
	    }
	    
	    // print arena
	    if (arena != null){
	    	leftalign(fmt, "Arena:", arena);
	    	fmt.format(newline());
	    }
	    
	    // print championships
	    if (championships != null && championships.size() > 0) {
	    	leftalign(fmt, "Championships:", championships.get(0));
	    	if (championships.size() > 1){
	    		for (String championship : championships.subList(1, championships.size())){
	    			leftalign(fmt, "", championship);
	    		}
	    	}
	    	fmt.format(newline());
	    }
	    
	    // print founded
	    if (founded != null){
	    	leftalign(fmt, "Founded:", founded);
	    	fmt.format(newline());
	    }
	    
	    // print leagues
	    if (leagues != null && leagues.size() > 0) {
	    	leftalign(fmt, "Leagues:", leagues.get(0).getName());
	    	if (leagues.size() > 1){
	    		for (League league : leagues.subList(1, leagues.size())){
	    			leftalign(fmt, "", league.getName());
	    		}
	    	}
	    	fmt.format(newline());
	    }
	    
	    // print locations
	    if (locations != null && locations.size() > 0) {
	    	leftalign(fmt, "Locations:", locations.get(0));
	    	if (locations.size() > 1){
	    		for (String location : locations.subList(1, locations.size())){
	    			leftalign(fmt, "Locations:", location);
	    		}
	    	}
	    	fmt.format(newline());
	    }
	    
	    // print coaches
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
	    
	    // print players
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
	    if (description != null){
	    	wrap(fmt, "Description", description);
	    	fmt.format(newline());
	    }
	    System.out.println(fmt); 
	}
	
	/**
	 * just for the big linebreak
	 * @return long line
	 */
	public String newline(){
		return " -------------------------------------------------------------------------------------------------- \n";
	}
	
	/**
	 * for the shorter linebreaks
	 * @return shorter line
	 */
	public String snewline(){
		return "-------------------------------------------------------------------------------";
	}
	
	/**
	 * center a string of text
	 * @param f formatter
	 * @param name the thing you want to center
	 */
	public void center(Formatter f, String name){
		String s = name;
		int length = s.length();
		int pad = (98-length)/2;
		String fmtstr = "|%" + Integer.toString(pad) + "s%" + Integer.toString(length) + "s%" + Integer.toString(pad) + "s|\n";
		f.format(fmtstr, "", name, "");
	}
	
	/**
	 * left-align an entry with one category label, and
	 * one corresponding field
	 * @param f formatter
	 * @param label category name
	 * @param value 
	 */
	public void leftalign(Formatter f, String label, String value){
		String fmtstr = "| %-17s%-80s|\n"; //97
		f.format(fmtstr, label, truncate(value, 80));
	}
	
	/**
	 * wrap a long piece of text designated by one category label
	 * @param f formatter
	 * @param label category name
	 * @param value
	 */
	public void wrap(Formatter f, String  label, String value){
		value = value.replace("\n", "");
		String fmtstr = "| %-17s%-80s|\n";
		int lines = value.length()/80;
		f.format(fmtstr, label, value.substring(0, 80));
		for (int i = 1; i < lines ; i++){
			f.format(fmtstr, "", value.substring(i * 80, i * 80 + 80));
		}
		f.format(fmtstr, "", value.substring(lines * 80, value.length()));
	}
	
	/**
	 * left-align an entry with one category label, and
	 * two corresponding fields
	 * @param f formatter
	 * @param label category name
	 * @param value1
	 * @param value2
	 */
	public void leftalign(Formatter f, String label, String value1, String value2){
		String fmtstr = "| %-17s| %-38s| %-38s|\n"; //93
		if (value1 == null) value1 = "";
		if (value2 == null) value2 = "";
		f.format(fmtstr, label, truncate(value1, 38), truncate(value2, 38));
	}
	
	/**
	 * left-align an entry with one category label, and
	 * three corresponding fields
	 * @param f formatter
	 * @param label category name
	 * @param value1
	 * @param value2
	 * @param value3
	 */
	public void leftalign(Formatter f, String label, String value1, String value2, String value3){
		String fmtstr = "| %-17s| %-25s| %-25s| %-24s|\n"; //91
		if (value1 == null) value1 = "";
		if (value2 == null) value2 = "";
		if (value3 == null) value3 = "";
		f.format(fmtstr, label, truncate(value1, 25), truncate(value2, 25), truncate(value3, 25));
	}
	
	/**
	 * left-align an entry with one category label, and
	 * four corresponding fields
	 * @param f formatter
	 * @param label category name
	 * @param value1
	 * @param value2
	 * @param value3
	 * @param value4
	 */
	public void leftalign(Formatter f, String label, String value1, String value2, String value3, String value4){
		String fmtstr = "| %-17s| %-18s| %-18s| %-18s| %-18s|\n"; //89
		if (value1 == null) value1 = "";
		if (value2 == null) value2 = "";
		if (value3 == null) value3 = "";
		if (value4 == null) value4 = "";
		f.format(fmtstr, label, truncate(value1, 18), truncate(value2, 18), truncate(value3, 18), truncate(value4, 18));
	}
	
	/**
	 * trim string to fit and append ellipses
	 * @param s String
	 * @param maxwidth
	 * @return formatted string
	 */
	public String truncate(String s, int maxwidth){
		if (s == null) return "";
		if (s.length() < maxwidth){
			return s;
		}
		String trimmed = s.substring(0, maxwidth-3) + "...";
		return trimmed;
	}
}
