package lti.entitycs4;

import java.util.Date;

public class Enroll {
	private Student student;
	private Course course;
	private Date enrollmentDate;
	
	public Enroll(Student student, Course course, Date enrollmentDate) {
		super();
		this.student = student;
		this.course = course;
		this.enrollmentDate = enrollmentDate;
	}
	
	public Enroll(Student student, Course course) {
		super();
		this.student = student;
		this.course = course;
		
	}
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Date getEnrollmentDate() {
		return enrollmentDate;
	}

	public void setEnrollmentDate(Date enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}



	@Override
	public String toString() {
		return "Enroll [student = " + student + ", course = " + course + ", enrollmentDate = " + enrollmentDate + "]";
	}
	
}
