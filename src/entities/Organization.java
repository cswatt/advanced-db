package entities;

public class Organization {
	
	private String name;
	
	//leadership position
	private String leaderRole;
	private String leaderTitle;
	private String leaderFrom;
	private String leaderTo;
	
	//board member
	private String boardMemberRole;
	private String boardMemberTitle;
	private String boardMemberFrom;
	private String boardMemberTo; 
	
	private boolean foundedBy;
	private boolean isLeaderOf;
	private boolean isMemberOf;
	
	public Organization(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getLeaderRole(){
		return this.leaderRole;
	}
	
	public void setLeaderRole(String leaderRole){
		this.leaderRole = leaderRole;
	}
	
	public String getLeaderTitle(){
		return this.leaderTitle;
	}
	
	public void setLeaderTitle(String leaderTitle){
		this.leaderTitle = leaderTitle;
	}
	
	public String getLeaderFrom(){
		return this.leaderFrom;
	}
	
	public void setLeaderFrom(String leaderFrom){
		this.leaderFrom = leaderFrom;
	}
	
	public String getLeaderTo(){
		return this.leaderTo;
	}
	
	public void setLeaderTo(String leaderTo){
		this.leaderTo = leaderTo;
	}
	
	public String getBoardMemberRole(){
		return this.boardMemberRole;
	}
	
	public void setBoardMemberRole(String boardMemberRole){
		this.boardMemberRole = boardMemberRole;
	}
	
	public String getBoardMemberTitle(){
		return this.boardMemberTitle;
	}
	
	public void setBoardMemberTitle(String boardMemberTitle){
		this.boardMemberTitle = boardMemberTitle;
	}
	
	public String getBoardMemberFrom(){
		return this.boardMemberFrom;
	}
	
	public void setBoardMemberFrom(String boardMemberFrom){
		this.boardMemberFrom = boardMemberFrom;
	}
	
	public String getBoardMemberTo(){
		return this.boardMemberTo;
	}
	
	public void setBoardMemberTo(String boardMemberTo){
		this.boardMemberTo = boardMemberTo;
	}
	
	public boolean getFoundedBy(){
		return this.foundedBy;
	}
	
	public void setFoundedBy(boolean foundedBy){
		this.foundedBy = foundedBy;
	}
	
	public boolean getIsMemberOf(){
		return this.isMemberOf;
	}
	
	public void setIsMemberOf(boolean isMemberOf){
		this.isMemberOf = isMemberOf;
	}
	
	public boolean getIsLeaderOf(){
		return this.isLeaderOf;
	}
	
	public void setIsLeaderOf(boolean isLeaderOf){
		this.isLeaderOf = isLeaderOf;
	}
}
