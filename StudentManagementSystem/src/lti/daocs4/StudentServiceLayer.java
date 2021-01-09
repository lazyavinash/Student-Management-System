package lti.daocs4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import lti.dbconnection.DbConnection;
import lti.entitycs4.Student;

public class StudentServiceLayer implements StudentInterface{
	DbConnection db = new DbConnection();
	Connection con;
	Scanner scan = new Scanner(System.in);
	PreparedStatement ps = null;
	Statement st = null;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	int i = 0;

	public int StudentRegister(Student student) {
		con = db.getConnection();
		try {
			ps = con.prepareStatement("insert into student values(?,?,?)");
			ps.setInt(1, student.getId());
			ps.setString(2, student.getName());
			java.sql.Date sqlDate = new java.sql.Date( student.getDob().getTime() ); 
			ps.setDate(3, sqlDate);
			i=ps.executeUpdate();

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


	public List<Student> ListOfStudents() {
		con = db.getConnection();

		String showAll = "select * from student";
		List<Student>studentlist = null;
		try {
			st = con.createStatement();
			ResultSet resultset = st.executeQuery(showAll);
			studentlist = new LinkedList<Student>();
			ResultSetMetaData resmet = resultset.getMetaData();
			System.out.println("<----------------------------------------------------------------------------->");
			System.out.println(resmet.getColumnName(1)+"\t\t"+resmet.getColumnName(2)+"\t\t"+resmet.getColumnName(3));

			while(resultset.next()) {
				studentlist.add(new Student(resultset.getInt(1),resultset.getString(2),resultset.getDate(3)));
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
		return studentlist;
	}


	public List<Student> StudentSearch(int studentID) {
		con = db.getConnection();
		System.out.println("<----------------------------------------------------------------------------->");
		String searchStudent = "select * from student where student_id = ?";
		List<Student> searchlist = null;
		try {
			ps = con.prepareStatement(searchStudent);
			ps.setInt(1,studentID);
			ResultSet res = ps.executeQuery();
			searchlist = new LinkedList<Student>();
			while(res.next()) {
				searchlist.add(new Student(res.getInt(1),res.getString(2),res.getDate(3)));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return searchlist;
	}
}
