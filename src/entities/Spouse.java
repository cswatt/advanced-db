package entities;

/**
 * @author Aikaterini Iliakopoulou (ai2315)
 * @author Cecilia Watt (ciw2104)
 * 
 * Class Spouse maps the necessary fields of a person's spouse. 
 */

public class Spouse {

	private String name;
	private String from;
	private String to;
	private String marriageLocation;
	
	public Spouse(String name){
		this.name = name;
	}
	
	/**
	 * Get & Set methods for fields name, from date, to date, place where they got married
	 * @return
	 */
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
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
	
	public String getMarriageLocation(){
		return this.marriageLocation;
	}
	
	public void setMarriageLocation(String location){
		this.marriageLocation = location;
	}
}
