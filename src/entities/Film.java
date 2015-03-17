package entities;

public class Film {
	
	private String name;
	private String character;
	
	public Film(String name, String character){
		this.name = name;
		this.character = character;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getCharacter(){
		return this.character;
	}
	
	public void setCharacter(String character){
		this.character = character;
	}
}
