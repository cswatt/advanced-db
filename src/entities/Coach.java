package entities;

import java.util.List;

/**
 * @author Aikaterini Iliakopoulou (ai2315)
 * @author Cecilia Watt (ciw2104)
 * 
 * Class Coach maps the necessary fields of a person who is an coach of a team. 
 */

public class Coach {
	
	private String name;
	private String from;
	private String to;
	
	private List<String> positions;
	
	public Coach(String name){
		this.name = name;
	}
	
	public Coach(String name, List<String> positions){
		this.name = name;
		this.positions = positions;
	}
	
	/**
	 * Get & Set methods for name, coach' positions, from date, to date
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
