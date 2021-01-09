 package lti.daocs4;
import java.util.List;

import lti.entitycs4.*;

interface StudentInterface {
	int StudentRegister(Student student);
	List<Student> ListOfStudents();
	List<Student> StudentSearch(int studentID);
}
