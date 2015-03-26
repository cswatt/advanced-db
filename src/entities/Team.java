package entities;

import java.util.List;

/**
 * @author Aikaterini Iliakopoulou (ai2315)
 * @author Cecilia Watt (ciw2104)
 * 
 * Class Team maps the necessary fields of a team. 
 */

public class Team {

	private String name;
	private String description;
	private String sport;
	private String arena;
	private String foundedIn;
	
	private List<String> championships;
	private List<String> locations;
	
	private List<Coach> coaches;
	private List<League> leagues;
	private List<Player> playersRoster;
	
	public Team(String name, String sport){
		this.name = name;
		this.sport = sport;
	}
	
	/**
	 * Get & Set methods for fields name, team's description, sport, team's arena, place where
	 * team was founded in, team's championships, team's locations, team's coaches, team's leagues,
	 * team's playersRoster
	 * @return
	 */
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getSport(){
		return this.sport;
	}
	
	public void setSport(String sport){
		this.sport = sport;
	}
	
	public String getArena(){
		return this.arena;
	}
	
	public void setArena(String arena){
		this.arena = arena;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getFoundedIn(){
		return this.foundedIn;
	}
	
	public void setFoundedIn(String foundedIn){
		this.foundedIn = foundedIn;
	}
	
	public List<String> getChampionships(){
		return this.championships;
	}
	
	public void setChampionships(List<String> championships){
		this.championships = championships;
	}
	
	public List<String> getLocations(){
		return this.locations;
	}
	
	public void setLocations(List<String> locations){
		this.locations = locations;
	}
	
	public List<Coach> getCoaches(){
		return this.coaches;
	}
	
	public void setCoaches(List<Coach> coaches){
		this.coaches = coaches;
	}
	
	public List<League> getLeagues(){
		return this.leagues;
	}
	
	public void setLeagues(List<League> leagues){
		this.leagues = leagues;
	}
	
	public List<Player> getPlayersRoster(){
		return this.playersRoster;
	}
	
	public void setPlayersRoster(List<Player> playersRoster){
		this.playersRoster = playersRoster;
	}
	
	/**
	 * Prints information about the team
	 */
	public void print(){
		System.out.println("---------------TEAM---------------");
		
		System.out.println("Name: "+ name);
		System.out.println("Sport: "+sport);
		System.out.println("Arena: "+arena);
		System.out.println("Founded in: "+foundedIn);
		System.out.println("Description: "+description);
		System.out.println();
		
		if(!locations.isEmpty()){
			System.out.println("Team "+this.name+" is the following locations:");
			for(String loc : locations){
				System.out.println(loc);
			}
		}
		System.out.println();
		if(!locations.isEmpty()){
			System.out.println("Team "+this.name+" has the following championships:");
			for(String champ : championships){
				System.out.println(champ);
			}
		}
		System.out.println();
		if(!leagues.isEmpty()){
			System.out.println("Team "+this.name+" has the following leagues:");
			for(League league : leagues){
				System.out.println(league.getName());
			}
		}
		System.out.println();
		if(!coaches.isEmpty()){
			System.out.println("Team "+this.name+" has the following coaches:");
			for(Coach coach : coaches){
				System.out.print(coach.getName() + " served in positions:");
				for(String pos : coach.getPositions())
					System.out.print(pos+" ");
				System.out.print(" from "+coach.getFrom()+" to "+coach.getTo());
				System.out.println();
			}
		}
		System.out.println();
		if(!playersRoster.isEmpty()){
			System.out.println("Team "+this.name+" has the following players:");
			for(Player player : playersRoster){
				System.out.print(player.getName() + "with number "+player.getNumber()+" served in positions:");
				for(String pos : player.getPositions())
					System.out.print(pos+" ");
				System.out.print(" from "+player.getFrom()+" to "+player.getTo());
				System.out.println();
			}
		}
	}
}
