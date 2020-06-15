package routineManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
	
	public static Connection doConnect()
	{
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/javawork","root","bce");
			System.out.println("Loaded!!");
			System.out.println("Connected!!");
		}
		catch (ClassNotFoundException | SQLException e) 
		{	
			e.printStackTrace();
		}
		return con;
	}

	public static void main(String[] args) {
		doConnect();
	}

}
