package lti.entitycs4;
//import java.time.LocalDate;
import java.util.Date;

public class Student {


	private int id;
	private String name;
	private Date dob;

	public Student() {

	}

	public Student(int id, String name, Date dob) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Date getDob() {
		return dob;
	}


	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String toString() {
		return "Student [id= " + id + ", name= " + name + ", dob= " + dob + "]";
	}


	/*
	 * @Override public String toString() { return "Student [id = " + id +
	 * ", name = " + name + ", dob = " + dob + "]"; }
	 * 
	 * @Override public int compareTo(Student o) { if(this.getId()>o.getId()) {
	 * return 1; } if(this.getId()<o.getId()) { return -1; } else return 0; }
	 */
}
