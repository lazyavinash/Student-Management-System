package lti.entitycs4;

public class Admin {
	private int admin_id;
	private String username;
	private String password;
	private String name;
	private String phNo;
	
	public Admin(String name, String phNo,String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.phNo = phNo;
	}
	public Admin(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhNo() {
		return phNo;
	}
	public void setPhNo(String phNo) {
		this.phNo = phNo;
	}
	public int getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}
	
}
