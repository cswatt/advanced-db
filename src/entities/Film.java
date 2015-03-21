package entities;

public class Film implements Comparable<Film>{
	
	private String name;
	private String character;
	
	public Film(String name, String character){
		this.name = name;
		this.character = character;
	}
	
	public String getName(){
		return (this.name == null) ? "" : this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getCharacter(){
		return (this.character == null) ? "" : this.character;
	}
	
	public void setCharacter(String character){
		this.character = character;
	}
	public int compareTo(Film otherfilm){
		String a = name;
		if (a==null) a = "";
		return a.compareTo(otherfilm.getName());
	}
	
}
