package lti.daocs4;

import java.util.Date;
import java.util.List;

import lti.entitycs4.Course;
import lti.entitycs4.Enroll;
import lti.entitycs4.Student;

interface EnrollInterface {
	int enrollStudent(Student student,Course course, Date date);
	List<Enroll> listAllEnrollment();
}
