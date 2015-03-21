package entities;

import java.util.ArrayList;
import java.util.List;

public class BusinessPerson extends Person{
	
	private List<Organization> organizations;
	private List<Organization> organizations_led = new ArrayList<Organization>();
	private List<Organization> organizations_onboard = new ArrayList<Organization>();
	private List<Organization> organizations_founded = new ArrayList<Organization>();
	
	public BusinessPerson(String name,String dateOfBirth,String placeOfBirth){
		super(name,dateOfBirth,placeOfBirth);
		
		this.setIsBusinessPerson(true);
	}
	
	public List<Organization> getOrganizations(){
		return this.organizations;
	}
	
	public void setOrganizations(List<Organization> organizations){
		this.organizations = organizations;
		sortOrgs();
		
	}
	
	public void sortOrgs(){
		for(Organization org : this.getOrganizations()){
			if(org.getFoundedBy()){
				this.organizations_founded.add(org);
			}
			if(org.getIsLeaderOf()){
				this.organizations_led.add(org);
			}
			if(org.getIsMemberOf()){
				this.organizations_onboard.add(org);
			}
		}
	}
	public List<Organization> getOrgsFounded(){
		return this.organizations_founded;
	}
	public List<Organization> getOrgsLed(){
		return this.organizations_led;
	}
	public List<Organization> getOrgsOnboard(){
		return this.organizations_onboard;
	}
	public void print(){
		
		if(this.getOrganizations().size() > 0){
			System.out.println("Organizations associated with:");
			for(Organization org : this.getOrganizations()){
				System.out.println();
				if(org.getFoundedBy())
					System.out.println(this.getName() + " founded "+org.getName());
				System.out.println();
				if(org.getIsLeaderOf()){
					System.out.println(this.getName() + " is leader of "+org.getName() + " with title "+org.getLeaderTitle() + ", role "+org.getLeaderRole()+" from "+org.getLeaderFrom()+" to "+org.getLeaderTo());
				}
				System.out.println();
				if(org.getIsMemberOf()){
					System.out.println(this.getName() + " is board member of "+org.getName() + " with title "+org.getBoardMemberTitle() + ", role "+org.getBoardMemberRole() +" from "+org.getBoardMemberFrom() +" to "+org.getBoardMemberTo());
				}
			}
		}
	}
	
}
