package lti.daocs4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import lti.dbconnection.DbConnection;
import lti.entitycs4.Course;

public class CourseServiceLayer implements CourseInterface {
	int i = 0;
	PreparedStatement ps = null;
	DbConnection db = new DbConnection();
	Connection con = null;
	Statement st = null;
	
	public int introduceCourse(Course course) {
		con=db.getConnection();
		String str = "insert into course values(?,?,?,?)";
		try {
			ps = con.prepareStatement(str);
			ps.setInt(1,course.getCourseCode());
			ps.setString(2, course.getCourseName());
			ps.setString(3,course.getCourseDuration());
			ps.setDouble(4, course.getCoursePrice());
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

	
	public List<Course> listAllCourses() {
		con = db.getConnection();
		String str = "select * from course";
		List<Course> courselist = null;
		try {
			st = con.createStatement();
			ResultSet courseset = st.executeQuery(str);
			courselist = new LinkedList<Course>();
			ResultSetMetaData resmet = courseset.getMetaData();
			System.out.println("<----------------------------------------------------------------------------->");
			System.out.println(resmet.getColumnName(1)+"\t\t"+resmet.getColumnName(2)+"\t\t"+resmet.getColumnName(3));

			while(courseset.next()) {
				courselist.add(new Course(courseset.getInt(1),courseset.getString(2),courseset.getString(3),courseset.getDouble(4)));
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
		return courselist;
	}



	public List<Course> CourseSearch(int courseId) {
		con = db.getConnection();
		String searchStudent = "select * from course where course_id = ?";
		List<Course> searchlist = null;
		try {
			searchlist = new LinkedList<Course>();
			ps = con.prepareStatement(searchStudent);
			ps.setInt(1,courseId);
			
			ResultSet res = ps.executeQuery();
				while(res.next()) {
				//System.out.println("Course Code: "+res.getInt(1)+"\n"+"Course Name: "+ res.getString(2)+"\nCourse Duration: "+res.getString(3)+"\nCourse Price: "+res.getDouble(4));
				searchlist.add(new Course(res.getInt(1),res.getString(2),res.getString(3),res.getDouble(4)));
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


