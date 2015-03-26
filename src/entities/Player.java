package entities;

import java.util.List;

/**
 * @author Aikaterini Iliakopoulou (ai2315)
 * @author Cecilia Watt (ciw2104)
 * 
 * Class Player maps the necessary fields of a player in a team. 
 */

public class Player {
	
	private String name;
	private String number;
	private String from;
	private String to;
	
	private List<String> positions;
	
	public Player(String name){
		this.name = name;
	}
	
	public Player(String name, List<String> positions){
		this.name = name;
		this.positions = positions;
	}
	
	/**
	 * Get & Set methods for fields name, player's number, from date, to date
	 * player's position within the team
	 * @return
	 */
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public List<String> getPositions(){
		return this.positions;
	}
	
	public void setPositions(List<String> positions){
		this.positions = positions;
	}
	
	public String getNumber(){
		return this.number;
	}
	
	public void setNumber(String number){
		this.number = number;
	}
	
	public String getFrom(){
		return this.from;
	}
	
	public void setFrom(String from){
		this.from = from;
	}
	
	public String getTo(){
		return this.to;
	}
	
	public void setTo(String to){
		this.to = to;
	}
}
