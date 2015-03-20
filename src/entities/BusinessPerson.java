package entities;

import java.util.List;

public class BusinessPerson extends Person{
	
	private List<Organization> organizations;
	
	public BusinessPerson(String name,String dateOfBirth,String placeOfBirth){
		super(name,dateOfBirth,placeOfBirth);
		
		this.setIsBusinessPerson(true);
	}
	
	public List<Organization> getOrganizations(){
		return this.organizations;
	}
	
	public void setOrganizations(List<Organization> organizations){
		this.organizations = organizations;
	}
	
	public void print(){
		
		if(this.getOrganizations().size() > 0){
			System.out.println("Organizations associated with:");
			for(Organization org : this.getOrganizations()){
				if(org.getFoundedBy())
					System.out.println(this.getName() + " founded "+org.getName());
				if(org.getIsLeaderOf()){
					System.out.println(this.getName() + " is leader of "+org.getName() + " with title "+org.getLeaderTitle() + ", role "+org.getLeaderRole()+" from "+org.getLeaderFrom()+" to "+org.getLeaderTo());
				}
				if(org.getIsMemberOf()){
					System.out.println(this.getName() + " is board member of "+org.getName() + " with title "+org.getBoardMemberTitle() + ", role "+org.getBoardMemberRole() +" from "+org.getBoardMemberFrom() +" to "+org.getBoardMemberTo());
				}
			}
		}
	}
	
}
