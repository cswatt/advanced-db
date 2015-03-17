package entities;

public class Player {
	
	private String name;
	private String position;
	private String number;
	private String from;
	private String to;
	
	public Player(String name, String position){
		this.name = name;
		this.position = position;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getPosition(){
		return this.position;
	}
	
	public void setPosition(String position){
		this.position = position;
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
