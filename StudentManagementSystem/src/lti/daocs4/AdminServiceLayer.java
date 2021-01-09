package lti.daocs4;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.sql.Statement;

import java.util.Scanner;

import lti.dbconnection.DbConnection;

public class AdminServiceLayer implements AdminInterface {
	DbConnection db = new DbConnection();
	Connection con;
	Scanner scan = new Scanner(System.in);
	PreparedStatement ps = null;
	Statement st = null;
	int i = 0;
	int j = 0;
	public int register(String username, String password, String name, String contact) {
		con = db.getConnection();
		String str = "insert into admin values(?,?,?,?)";
		try {
			ps = con.prepareStatement(str);
			ps.setString(1, name);
			ps.setString(2, contact);
			ps.setString(3, username);
			ps.setString(4, password);
			
			i = ps.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return i;
	}

	
	public int signin(String username, String password) {
		con = db.getConnection();
		String str = "select * from admin where admin_username = ? and admin_password = ?";
		try {
			ps = con.prepareStatement(str);
			ps.setString(1, username);
			ps.setString(2, password);
			
			j = ps.executeUpdate();
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
		return j;
	}

}
