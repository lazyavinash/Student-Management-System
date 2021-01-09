package lti.dbconnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DbConnection {
    final String driver="oracle.jdbc.OracleDriver";
    final String username="system";
    final String password="oracle";
    final String url="jdbc:oracle:thin:@localhost:1521:orcl";
    
    private static Connection con;
//method to create connection...
    
        public Connection getConnection(){
        try {
            //Step1- load driver..
            Class.forName(driver);
            //Step2-Connection to DB
            con=DriverManager.getConnection(url,username,password);
            System.out.println("Connection :"+con);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}