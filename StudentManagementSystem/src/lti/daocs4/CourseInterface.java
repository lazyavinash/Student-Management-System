package lti.daocs4;

import java.util.List;

import lti.entitycs4.Course;

interface CourseInterface {
	int introduceCourse(Course course);
	List<Course> listAllCourses();
	List<Course> CourseSearch(int courseId);
}
