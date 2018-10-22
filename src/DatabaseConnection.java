

import java.sql.*;

public class DatabaseConnection {
	public static Connection getConnection() {
		Connection connection = null;
		
		try {			
			Class.forName("com.mysql.jdbc.Driver");
			
			String user = "xxxxx";
			String pass = "xxxxx";
			String url = "jdbc:mysql://localhost:3306/Library?autoReconnect=true&useSSL=false";
			
			connection = DriverManager.getConnection(url, user, pass);
	
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return connection;
	}

}
