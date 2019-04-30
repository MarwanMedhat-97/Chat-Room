import java.sql.*;

public class DBHandler {

	public DBHandler() {
		// TODO Auto-generated constructor stub
	}

	public static Connection getConn() {
		
		  try{
	            Class.forName("com.mysql.jdbc.Driver");
	            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3308/aptchat","root","");
	            System.out.println("Connected to DB Successfully");
	            return conn;
	        }catch(Exception ex){
	            System.out.println(ex);
	            System.out.println("Connection to DB Failed");
	            return null;
	        }
	}
}
