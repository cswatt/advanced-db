package result;
/**
 * @author Cecilia Watt (ciw2104)
 * @author Aikaterini Iliakopoulou (ai2315)
 * 
 * Represents a person who performed as a role in creating/doing something.
 * For instance, (Jonathan Franzen, author)
 */
public class Role implements Comparable<Role> {
	private String name;
	private String job;
	
	/**
	 * Creates a Role
	 * @param name   name of the person
	 * @param job    the job they performed
	 */
	public Role(String name, String job){
		this.name = name;
		this.job = job;
	}
	/**
	 * Sets the name of the person
	 * @param name
	 */
	public void setName(String name){
		this.name = name;
	}
	/**
	 * Returns the name of the person
	 * @return
	 */
	public String getName(){
		return this.name;
	}
	/**
	 * sets the job of the person
	 * @param name
	 */
	public void setJob(String name){
		this.job = job;
	}
	/**
	 * returns the job of the person
	 * @return
	 */
	public String getJob(){
		return this.job;
	}
	/**
	 * Returns a formatted string, e.g.
	 * Jonathan Franzen (as author)
	 */
	public String toString(){
		return this.name + " (as " + this.job + ")";
	}
	/**
	 * For comparing names
	 */
	public int compareTo(Role otherrole) {
		return this.name.compareTo(otherrole.getName());
	}

}
