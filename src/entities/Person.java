package entities;

import java.util.List;


public class Person {
	
	private String name;
	private String dateOfBirth;
	private String placeOfBirth;
	private String placeOfDeath;
	private String dateOfDeath;
	private String causeOfDeath;
	
	private List<String> siblings;
	private List<Spouse> spouses;
	
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
	
	public List<Spouse> getSpouses(){
		return this.spouses;
	}
	
	public void setSpouses(List<Spouse> spouses){
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

	public void print(){
		System.out.println("---------------PERSON---------------");
		System.out.println("Name: "+ name);
		System.out.println("Date Of Birth: "+dateOfBirth);
		System.out.println("Place Of Birth: "+placeOfBirth);
		
		if(dateOfDeath != null && !dateOfDeath.equals(""))
			System.out.println("Date Of Death: "+dateOfDeath);
		if(placeOfDeath != null && !placeOfDeath.equals(""))
			System.out.println("Place Of Death: "+placeOfDeath);
		if(causeOfDeath != null && !causeOfDeath.equals(""))
			System.out.println("Cause Of Death: "+causeOfDeath);
		
		if(siblings.size() > 0){
			System.out.print("Siblings: ");
			for(String sibling : siblings)
				System.out.println("\t"+sibling+" ");
			System.out.println();
		}
		if(spouses.size() > 0){
			System.out.print("Spouses: ");
			for(Spouse spouse : spouses)
				System.out.println("\t"+spouse.getName()+" from "+spouse.getFrom()+" to "+spouse.getTo()+" at "+spouse.getMarriageLocation());
			System.out.println();
		}
		
		if(description!=null && !description.equals("")){
			System.out.println("Description: "+description);
		}
		System.out.println();
		if(isBusinessPerson){
			System.out.println("He is a business person: YES");
		}
		else
			System.out.println("He is a business person: NO");
		
		if(isAuthor)
			System.out.println("He is an author: YES");
		else
			System.out.println("He is an author: NO");
		
		if(isActor)
			System.out.println("He is an actor: YES");
		else
			System.out.println("He is an actor: NO");
	}
}
