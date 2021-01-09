package lti.maincs4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import lti.daocs4.CourseServiceLayer;
import lti.daocs4.EnrollServiceLayer;
import lti.daocs4.StudentServiceLayer;
import lti.entitycs4.Course;
import lti.entitycs4.Enroll;
import lti.entitycs4.Student;

public class AppMain_v2 {
	public static void main(String[] args) {
		introduce();
	}
	public static void introduce() {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
		StudentServiceLayer ssl = new StudentServiceLayer();
		CourseServiceLayer csl = new CourseServiceLayer();
		EnrollServiceLayer esl = new EnrollServiceLayer();
		Scanner scan = new Scanner(System.in);
		int ch;
		int sh;
		int i;
		int j;
		int k;
		int studentid = 0;
		String studentname = null;
		Date studentdob = null;
		int courseid = 0;
		String coursename = null;
		String courseduration = null;
		double courseprice = 0;
		boolean showStudentMain = false;
		boolean showAdminMain = false;
		boolean showMain = true;
		boolean showAppMain = true;

		do {
			System.out.println("<------------------------------STUDENT MANAGEMENT SYSTEM V4-------------------------------->");
			while(showMain) {
				System.out.println("                             ><------------<Login>-------------><");
				System.out.println("Login as: \n\n1. Student\t 2. Admin");
				int choice = scan.nextInt();
				switch(choice) {
				case 1:
					showStudentMain = true;
					showMain = false;
					break;
				case 2:
					showAdminMain = true;
					showMain = false;
					break;
				default:
					System.out.println("Invalid input. Try Again.");
					break;
				}
			}

			while(showStudentMain) {
				System.out.println("<-----------------------------<><><STUDENT WINDOW><><>--------------------------->");
				System.out.println("1. Register \t2. Enroll to a Course   \t3. View Courses ");
				ch= scan.nextInt();
				if(ch==1) {
					System.out.println("<--------------------STUDENT REGISTRATION WINDOW------------------------>");
					System.out.println("Enter the Student ID: ");
					int studentId = scan.nextInt();
					scan.nextLine();
					System.out.println("Enter Student Name: ");
					String studentName = scan.nextLine();
					System.out.println("Enter Student DOB(dd-MM-yyyy): ");
					String sDob = scan.nextLine();
					try {
						Date dDob = dateFormatter.parse(sDob);
						java.sql.Date sqlDate = new java.sql.Date( dDob.getTime() ); 
						i = ssl.StudentRegister(new Student(studentId,studentName,sqlDate));
						if(i>0) {
							System.out.println("Record added to student database.");
						}else {
							System.out.println("Record could not be added. Go back to main");
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
					System.out.println("<----------------------------------------------------------------------------->");
				}
				
				else if(ch==2) {
					System.out.println("<------------------------------------<STUDENT> ENROLL COURSE WINDOW----------------------------------------->");
					System.out.println("Enter your Studetn ID: ");
					int studentId = scan.nextInt();
					scan.nextLine();
					List<Student> stulist = ssl.StudentSearch(studentId);
					for(Student s: stulist) {
						studentid = s.getId();
						studentname = s.getName();
						studentdob = s.getDob();
					}
					Student stunew = new Student(studentid,studentname,studentdob);
					System.out.println("\t<---------------------------------COURSE SELECTION------------------------------------>");
					List<Course> courselist = csl.listAllCourses();
					for(Course c:courselist) {
						System.out.println("\n"+c.getCourseCode()+"\t\t"+c.getCourseName()+"\t\t"+c.getCourseDuration()+
								"\t\t"+c.getCoursePrice());
					}
					System.out.println("Enter the Course Code you wish to enroll: ");
					int courseCode = scan.nextInt();
					scan.nextLine();
					List<Course> courlist = csl.CourseSearch(courseCode);
					for(Course c: courlist) {
						courseid = c.getCourseCode();
						coursename = c.getCourseName();
						courseduration = c.getCourseDuration();
						courseprice = c.getCoursePrice();
					}
					Course cournew = new Course(courseid,coursename,courseduration,courseprice);
					System.out.println("Enter Date of Enrollment: ");
					String sDoe = scan.nextLine();
					try {
						Date dDoe = dateFormatter.parse(sDoe);
						java.sql.Date sqlDate = new java.sql.Date( dDoe.getTime()); 
						k=esl.enrollStudent(stunew,cournew,sqlDate);
						if(k>0) {
							System.out.println("<----------------------------------------------------------------------------->");
							System.out.println("Student: "+studentname+" with Student ID: "+studentid+"\nEnrolled for Course: "+ courseid+" "+coursename);
							System.out.println("<-----------------------------------ENROLLED SUCCESSFULLY---------------------------------------->");
						}else {
							System.out.println("Student Enroll Unsuccessful.");
						}
					} catch (Exception e) {
						System.out.println("\t-----------------------------------------------------------------");
						System.out.println("\t|Student needs to REGISTER first or wrong COURSE CODE entered.|");
						System.out.println("\t-----------------------------------------------------------------");
					}

				}

				else if(ch==3) {
					System.out.println("<-------------------------------<ADMIN/STUDENT><VIEW COURSES-------------------------------->");
					List<Course> courselist = csl.listAllCourses();
					for(Course c:courselist) {
						System.out.println("\n"+c.getCourseCode()+"\t\t"+c.getCourseName()+"\t\t"+c.getCourseDuration()+
								"\t\t"+c.getCoursePrice());
					}
				}
				else if(ch==4) {
					System.out.println("Enter the Course Code:");
					List<Course> searchlist = csl.CourseSearch(scan.nextInt());
					System.out.println("<----------------------------------------------------------------------------->");
					for(Course c:searchlist) {
						System.out.println("Course Code: "+c.getCourseCode()+"\n"+"Course Name: "+ c.getCourseName()+
								"\nCourse Duration: "+c.getCourseDuration()+"\nCourse Price: "+c.getCoursePrice());
						System.out.println("<----------------------------------------------------------------------------->");
					}
				}
				else {
					System.out.println("Wrong input.");
				}
				System.out.println("Press 1. to return back to Student Main (or) 2. to go back to login page. (or) 3. to exit");
				int best = scan.nextInt();
				if(best==1) {
					showStudentMain = true;
					//showAdminLoginMain = false;
					showAppMain = true;
					showMain = false;
				}else if(best==2) {
					showMain = true;
					showStudentMain=false;
					showAppMain = true;
				}
				else {
					showStudentMain = false;
					showAppMain = false;
					System.out.println("                   -----------------THANK YOU VISIT AGAIN--------------------");
				}
			}

			while(showAdminMain) {
				System.out.println("<-----------------------------<><><ADMIN WINDOW><><>--------------------------->");
				System.out.println("1. Introduce Course \n2. View Course List \n3. Search Course \n\n4. View Student List"
						+ " \n5. Search Student \n\n6. View Enrollments");
				sh = scan.nextInt();
				if(sh==1) {
					System.out.println("<--------------------<ADMIN><COURSE INRODUCING WINDOW>------------------------>");
					System.out.println("Enter the Course ID: ");
					int courseId = scan.nextInt();
					scan.nextLine();
					System.out.println("Enter Course Name: ");
					String courseName = scan.nextLine();
					System.out.println("Enter Course Duration: ");
					String courseDuration = scan.nextLine();
					System.out.println("Enter the Course Price: ");
					double coursePrice = scan.nextDouble();
					j=csl.introduceCourse(new Course(courseId,courseName,courseDuration,coursePrice));
					if(j>0) {
						System.out.println("Course Added successfully to the database.");
					}else {
						System.out.println("Unable to add course to the database.");
					}
				}
				else if(sh==2) {
					System.out.println("<-------------------------------<ADMIN/STUDENT> VIEW COURSES-------------------------------->");
					List<Course> courselist = csl.listAllCourses();
					for(Course c:courselist) {
						System.out.println("\n"+c.getCourseCode()+"\t\t"+c.getCourseName()+"\t\t"+c.getCourseDuration()+
								"\t\t"+c.getCoursePrice());
					}
				}
				else if(sh==3) {
					System.out.println("Enter the Course Code:");
					List<Course> searchlist = csl.CourseSearch(scan.nextInt());
					System.out.println("<----------------------------------------------------------------------------->");
					for(Course c:searchlist) {
						System.out.println("Course Code: "+c.getCourseCode()+"\n"+"Course Name: "+ c.getCourseName()+
								"\nCourse Duration: "+c.getCourseDuration()+"\nCourse Price: "+c.getCoursePrice());
						System.out.println("<----------------------------------------------------------------------------->");
					}
				}
				else if(sh==4) {
					System.out.println("<-------------------------------<ADMIN> VIEW STUDENTS-------------------------------->");
					List<Student> studentlist = ssl.ListOfStudents();
					for(Student s:studentlist) {
						System.out.println("\n"+s.getId()+"\t\t"+s.getName()+"\t\t"+s.getDob());
					}
					System.out.println("<----------------------------------------------------------------------------->");

				}
				else if(sh==5) {
					System.out.println("Enter the Student ID:");
					List<Student> searchlist = ssl.StudentSearch(scan.nextInt());
					for(Student s: searchlist) {
						System.out.println("\nStudent ID: "+s.getId()+"\nStudent Name: "+s.getName()+"\nStudent DOB: "+s.getDob());
					}
					System.out.println("<----------------------------------------------------------------------------->");

				}
				else if(sh==6) {
					System.out.println("<-------------------------------<ADMIN> VIEW ENROLLMENTS-------------------------------->");
					List<Enroll> enrolllist = esl.listAllEnrollment();
					for(Enroll e:enrolllist) {
						System.out.println("\n\t"+e.getStudent().getId()+"\t\t"+e.getStudent().getName() +"\t\t"+e.getCourse().getCourseCode()+"\t\t"+e.getCourse().getCourseName()+"\t\t"+e.getEnrollmentDate());
					}
				}
				else {
					System.out.println("Wrong Input");

				}
				System.out.println("Press 1 to go back to Admin Window. (or) 2. to go back to login page (or) 3. to exit");
				int best2 = scan.nextInt();
				if(best2==1) {
					showAppMain = true;
					showAdminMain = true;
					showMain = false;
					showStudentMain = false;
				}else if(best2==2) {
					showAppMain = true;
					showMain = true;
					showAdminMain=false;
				}else {
					showAppMain = false;
					showAdminMain = false;
					System.out.println("                   -----------------THANK YOU VISIT AGAIN--------------------");
				}

			}
		}while(showAppMain);	
		scan.close();
	}
}