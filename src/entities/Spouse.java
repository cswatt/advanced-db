package entities;

public class Spouse {

	private String name;
	private String from;
	private String to;
	private String marriageLocation;
	
	public Spouse(String name){
		this.name = name;
	}
	
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
