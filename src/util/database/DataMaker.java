package util.database;

import java.sql.Connection;
import java.sql.SQLException;

import control.Config;

public class DataMaker {

	
	public static String DATABASE_ADDRESS = "jdbc:localhost:3306";
	public static String DATABASE_USER_NAME = "root";
	public static String DATABASE_PASSWORD = "passWORD,233";
	public static void config()
	{
		
		String name = null;
		name = Config.getString("DATABASE_ADDRESS");
		if(name != null) DATABASE_ADDRESS = name;
		System.out.println(name);
		name = Config.getString("DATABASE_USER_NAME");
		if(name != null) DATABASE_USER_NAME=name;
		name = Config.getString("DATABASE_PASSWORD");
		if(name != null) DATABASE_PASSWORD=name;

		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Config.init();
		config();
		
		Connection conn = null;
		try {
			conn = JDBCUtils.connect(DATABASE_ADDRESS, DATABASE_USER_NAME, DATABASE_PASSWORD);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Integer i=1000;i<=6275;++i)
		{
			String BASE="http://acm.hdu.edu.cn/showproblem.php?pid=";
			String URL = BASE+i.toString();
			try {
				JDBCUtils.insert(conn, "urlList", "pk_url,state", URL+",1");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				continue;
			}
			
		}
	}

}
