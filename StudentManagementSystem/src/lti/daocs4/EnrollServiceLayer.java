package lti.daocs4;

import lti.dbconnection.DbConnection;
import lti.entitycs4.Course;
import lti.entitycs4.Enroll;
import lti.entitycs4.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
//import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedList;
//import java.util.LinkedList;
import java.util.List;

public class EnrollServiceLayer implements EnrollInterface {
	int i;
	DbConnection db = new DbConnection();
	Connection con;
	PreparedStatement ps = null;
	Statement st = null;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	
	public int enrollStudent(Student student, Course course, Date date ) {
		con = db.getConnection();
		String str = "insert into enroll values(?,?,?,?,?,?,?,?)";
		try {
			ps = con.prepareStatement(str);
			ps.setInt(1, student.getId());
			ps.setString(2,student.getName());
			java.sql.Date sqlDate = new java.sql.Date( student.getDob().getTime() ); 
			ps.setDate(3, sqlDate);
			ps.setInt(4,course.getCourseCode());
			ps.setString(5,course.getCourseName());
			ps.setString(6,course.getCourseDuration());
			ps.setDouble(7,course.getCoursePrice());
			java.sql.Date sqlDate1 = new java.sql.Date( date.getTime() ); 
			ps.setDate(8, sqlDate1 );
			
			i = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return i;
	}


	public List<Enroll> listAllEnrollment() {
		con = db.getConnection();
		String str = "select * from enroll";
	
		List<Enroll>courlist = null;
		try {
			st = con.createStatement();
			ResultSet resultset = st.executeQuery(str);
			courlist = new LinkedList<Enroll>();
			ResultSetMetaData resmet = resultset.getMetaData();
			System.out.println("<----------------------------------------------------------------------------->");
			System.out.println(resmet.getColumnName(1)+"\t\t"+resmet.getColumnName(2)+"\t\t"+resmet.getColumnName(4)+"\t\t"+resmet.getColumnName(5)+"\t\t"+resmet.getColumnName(8));
			while(resultset.next()) {
				courlist.add(new Enroll(new Student(resultset.getInt(1),resultset.getString(2),resultset.getDate(3)),new Course(resultset.getInt(4),resultset.getString(5),resultset.getString(6),resultset.getDouble(7)),resultset.getDate(8)));
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return courlist;
	}
	
}
