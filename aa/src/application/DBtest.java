package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBtest 
{
	public static String driver = "oracle.jdbc.driver.OracleDriver";
	public static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	public static String user = "System";
	public static String password = "1234";
	public static Connection dbConn;
	
	public static Connection dbconnect()
	{	
		try 
		{
			Class.forName(driver);
			System.out.println("jdbc driver 로딩 성공");
			dbConn = DriverManager.getConnection(url, user, password);
			System.out.println("오라클 연결 성공");
		} 
		
		catch (ClassNotFoundException e) 
		{
			System.out.println("jdbc driver 로딩 실패");
		} 
		
		catch (SQLException e) 
		{
			System.out.println("오라클 연결 실패");
		}
		
		return dbConn;
	}
}
