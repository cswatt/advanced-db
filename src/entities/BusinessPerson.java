package entities;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Aikaterini Iliakopoulou (ai2315)
 * @author Cecilia Watt (ciw2104)
 * 
 * Class BusinessPerson maps the necessary fields of a person who is an business person. 
 */

public class BusinessPerson extends Person{
	
	private List<Organization> organizations;
	private List<Organization> organizations_led = new ArrayList<Organization>();
	private List<Organization> organizations_onboard = new ArrayList<Organization>();
	private List<Organization> organizations_founded = new ArrayList<Organization>();
	
	public BusinessPerson(String name,String dateOfBirth,String placeOfBirth){
		super(name,dateOfBirth,placeOfBirth);
		
		this.setIsBusinessPerson(true);
	}
	
	/**
	 * Get & Set methods for fields organizations the person is generally associated with, 
	 * organizations the person is a leader of, organizations the person is a board member of,
	 * organizations the person has founded
	 * @return
	 */
	
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
	
	/**
	 * Prints information about the business person
	 * @return
	 */
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
