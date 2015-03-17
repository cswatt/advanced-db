package entities;

import java.util.List;

public class Team {

	private String name;
	private String description;
	private String sport;
	private String arena;
	private String foundedBy;
	
	private List<String> championships;
	private List<String> locations;
	
	private List<Coach> coaches;
	private List<League> leagues;
	private List<Player> playersRoaster;
	
	public Team(String name, String sport){
		this.name = name;
		this.sport = sport;
	}
	
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
	
	public String getFoundedBy(){
		return this.foundedBy;
	}
	
	public void setFoundedBy(String foundedBy){
		this.foundedBy = foundedBy;
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
	
	public List<Player> getPlayersRoaster(){
		return this.playersRoaster;
	}
	
	public void setPlayersRoaster(List<Player> playersRoaster){
		this.playersRoaster = playersRoaster;
	}
}
