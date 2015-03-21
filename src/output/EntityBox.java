package output;

import java.util.Arrays;
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
		if (p==null){
			System.out.println("no person?");
		}
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
	    if (siblings.size() > 0) {
	    	fmt.format("| %-20s %-80s|\n", "Sibling(s):", siblings.get(0));
	    	if (siblings.size() > 1){
	    		for (String sibling : siblings.subList(1, siblings.size())){
	    			fmt.format("| %-20s %-80s|\n", "", sibling);
	    		}
	    	}
	    	fmt.format(newline());
	    }
	    if (spouses.size() > 0) {
	    	fmt.format("| %-20s %-80s|\n", "Spouse(s):", spouses.get(0));
	    	if (spouses.size() > 1){
	    		for (String spouse : spouses.subList(1, spouses.size())){
	    			fmt.format("| %-20s %-80s|\n", "", spouse);
	    		}
	    	}
	    	fmt.format(newline());
	    }
	    // and description
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
		
		
		System.out.println("BUSINESSPERSON");
		Formatter fmt = new Formatter();
		if (organizations_led.size() > 0) {
			fmt.format("| %-20s|%-20s|%-19s|%-19s|%-19s|\n", "Leadership:", "Organization", "Role", "Title", "From-To");
			for (Organization org : organizations_led){
				fmt.format("| %-20s %-80s|\n", "",snewline());
				fmt.format("| %-20s|%.20s|%.19s|%.19s|%.19s|\n", "", org.getName(), org.getLeaderRole(), org.getLeaderTitle(), org.getLeaderFrom() + " - " + org.getLeaderTo());
			}
	    	fmt.format(newline());
	    }
		if (organizations_onboard.size() > 0) {
			fmt.format("| %-20s|%-20s|%-19s|%-19s|%-19s|\n", "Leadership:", "Organization", "Role", "Title", "From-To");
			for (Organization org : organizations_onboard){
				fmt.format("| %-20s %-80s|\n", "",snewline());
				fmt.format("| %-20s|%.20s|%.19s|%.19s|%.19s|\n", "", org.getName(), org.getBoardMemberRole(), org.getBoardMemberTitle(), org.getBoardMemberFrom() + " - " + org.getBoardMemberTo());
			}
	    	fmt.format(newline());
	    }
		if (organizations_founded.size() > 0) {
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
	}
	public void printActor(){
		Actor a = result.getActor();
		if (a==null){
			System.out.println("no actor");
		}
		System.out.println("ACTOR");
	}
	public void printLeague(){
		League l = result.getLeague();
		if (l==null){
			System.out.println("no league");
		}
		System.out.println("LEAGUE");
	}
	public void printTeam(){
		Team t = result.getTeam();
		if (t==null){
			System.out.println("no team");
		}
		System.out.println("TEAM");
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
