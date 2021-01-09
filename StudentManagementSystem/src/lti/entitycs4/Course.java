package lti.entitycs4;

public class Course {
	private int courseCode;
	private String courseName;
	private String courseDuration;
	private double coursePrice;
	
	public Course() {
		
	}
	
	public Course(int courseCode, String courseName, String courseDuration, double coursePrice) {
		super();
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.courseDuration = courseDuration;
		this.coursePrice = coursePrice;
	}
	
	public int getCourseCode() {
		return courseCode;
	}
	public String getCourseName() {
		return courseName;
	}	
	

	public String getCourseDuration() {
		return courseDuration;
	}

	public double getCoursePrice() {
		return coursePrice;
	}

	public void setCoursePrice(double coursePrice) {
		this.coursePrice = coursePrice;
	}

	public void setCourseCode(int courseCode) {
		this.courseCode = courseCode;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public void setCourseDuration(String courseDuration) {
		this.courseDuration = courseDuration;
	}

	public String toString() {
		return "Course [courseCode= " + courseCode + ", courseName= " + courseName + ", courseDuration= " + courseDuration+"]";
	}

	
}
