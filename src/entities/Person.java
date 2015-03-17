package entities;

import java.util.List;


public abstract class Person {
	
	private String name;
	private String dateOfBirth;
	private String placeOfBirth;
	private String placeOfDeath;
	private String dateOfDeath;
	private String causeOfDeath;
	
	private List<String> siblings;
	private List<String> spouses;
	
	private String description;
	
	private boolean isBusinessPerson = false;
	private boolean isAuthor = false;
	private boolean isActor = false;
	
	public Person(String name, String dateOfBirth, String placeOfBirth){
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.placeOfBirth = placeOfBirth;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getDateOfBirth(){
		return this.dateOfBirth;
	}
	
	public void setDateOfBirth(String dateOfBirth){
		this.dateOfBirth = dateOfBirth;
	}
	
	public String getPlaceOfBirth(){
		return this.placeOfBirth;
	}
	
	public void setPlaceOfBirth(String placeOfBirth){
		this.placeOfBirth = placeOfBirth;
	}
	
	public String getPlaceOfDeath(){
		return this.placeOfDeath;
	}
	
	public void setPlaceOfDeath(String placeOfDeath){
		this.placeOfDeath = placeOfDeath;
	}
	
	public String getDateOfDeath(){
		return this.dateOfDeath;
	}
	
	public void setDateOfDeath(String dateOfDeath){
		this.dateOfDeath = dateOfDeath;
	}
	
	public String getCauseOfDeath(){
		return this.causeOfDeath;
	}
	
	public void setCauseOfDeath(String causeOfDeath){
		this.causeOfDeath = causeOfDeath;
	}
	
	public List<String> getSiblings(){
		return this.siblings;
	}
	
	public void setSiblings(List<String> siblings){
		this.siblings = siblings;
	}
	
	public List<String> getSpouses(){
		return this.spouses;
	}
	
	public void setSpouses(List<String> spouses){
		this.spouses = spouses;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public boolean isBusinessPerson(){
		return this.isBusinessPerson;
	}
	
	public void setIsBusinessPerson(boolean isBusinessPerson){
		this.isBusinessPerson = isBusinessPerson;
	}
	
	public boolean isActor(){
		return this.isActor;
	}
	
	public void setIsActor(boolean isActor){
		this.isActor = isActor;
	}
	
	public boolean isAuthor(){
		return this.isAuthor;
	}
	
	public void setIsAuthor(boolean isAuthor){
		this.isAuthor = isAuthor;
	}
}
