package result;

public class Role implements Comparable<Role> {
	private String name;
	private String job;
	public Role(String name, String job){
		this.name = name;
		this.job = job;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setJob(String name){
		this.job = job;
	}
	public String getJob(){
		return this.job;
	}
	public String toString(){
		return this.name + " (as " + this.job + ")";
	}
	public int compareTo(Role otherrole) {
		return this.name.compareTo(otherrole.getName());
	}

}
