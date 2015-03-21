package entities;

import java.util.List;

public class League {
	
	private String name;
	private String championship;
	private String sport;
	private String slogan;
	private String website;
	private String description;
	
	private List<Team> teams;
	
	public League(String name){
		this.name = name;
	}
	
	public League(String name, String sport, String championship){
		this.name = name;
		this.sport = sport;
		this.championship = championship;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getChampionship(){
		return this.championship;
	}
	
	public void setChampionship(String championship){
		this.championship = championship;
	}
	
	public String getSport(){
		return this.sport;
	}
	
	public void setSport(String sport){
		this.sport = sport;
	}
	
	public String getSlogan(){
		return this.slogan;
	}
	
	public void setSlogan(String slogan){
		this.slogan = slogan;
	}
	
	public String getWebsite(){
		return this.website;
	}
	
	public void setWebsite(String website){
		this.website = website;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public List<Team> getTeams(){
		return this.teams;
	}
	
	public void setTeams(List<Team> teams){
		this.teams = teams;
	}
	
	public void print(){
		System.out.println("---------------LEAGUE---------------");
		
		System.out.println("Name: "+ name);
		System.out.println("Sport: "+sport);
		System.out.println("Slogan: "+slogan);
		System.out.println("Championship: "+ championship);
		System.out.println("Website: "+website);
		System.out.println("Description: "+description);
		System.out.println();
		if(!teams.isEmpty()){
			System.out.println("League "+this.name+" has the following teams:");
			for(Team team : teams){
				System.out.println(team.getName());
			}
		}
	}
	
}
