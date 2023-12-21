package model;

import java.io.Serial;

public class Manager  extends User{

	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 6700902789826811231L;
private String access_level; 
	
	public Manager(String firstName, String lastName, String username, String password ,String phone, String profession, String salary) {
		super(firstName, lastName, username, password ,phone , profession,salary);
	
	}

	public String getAccess_level() {
		return access_level;
	}

	public void setAccess_level(String access_level) {
		this.access_level = access_level;
	}

	@Override
	public String toString() {
		return "Manager [access_level=" + access_level + "]";
	}
}
