package entities;

import java.util.List;

public class BusinessPerson extends Person{
	
	private List<Organization> organizations;
	
	public BusinessPerson(String name,String dateOfBirth,String placeOfBirth){
		super(name,dateOfBirth,placeOfBirth);
	}
	
	public List<Organization> getOrganizations(){
		return this.organizations;
	}
	
	public void setOrganizations(List<Organization> organizations){
		this.organizations = organizations;
	}
	
}
