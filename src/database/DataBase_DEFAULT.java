package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.Page;
import control.Config;
import interfaces.DataBase;
import util.database.ConnectionPool;
import util.database.JDBCUtils;

public class DataBase_DEFAULT implements DataBase{

	
	public static String DATABASE_ADDRESS = "jdbc:localhost:3306";
	public static String DATABASE_USER_NAME = "root";
	public static String DATABASE_PASSWORD = "passWORD,233";
	private static boolean needConfig = true;
	public static int CONNECTION_POOL_SIZE=3;
	private  static ConnectionPool connectionPool = null;
	
	public static void config()
	{
		if(!needConfig)return;
		needConfig = false;
		String name = null;
		Integer number = null;
		name = Config.getString("DATABASE_ADDRESS");
		if(name != null) DATABASE_ADDRESS=name;
		name = Config.getString("DATABASE_USER_NAME");
		if(name != null) DATABASE_USER_NAME=name;
		name = Config.getString("DATABASE_PASSWORD");
		if(name != null) DATABASE_PASSWORD=name;
		number = Config.getInteger("CONNECTION_POOL_SIZE");
		if(number != null)CONNECTION_POOL_SIZE=number;
		connectionPool = new ConnectionPool(DATABASE_ADDRESS,DATABASE_USER_NAME,DATABASE_PASSWORD,CONNECTION_POOL_SIZE);
	}
	


	public DataBase_DEFAULT()
	{
		config();
		System.out.println("config_finish");
	
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
//		String sql = "begin; set @url = '233'; select @url := pk_url from urlList where state = 3 ; update urlList set state = 1 where pk_url = @url; select @url; commit;";
		
		DATABASE_ADDRESS = "jdbc:mysql://59.110.155.203:3306/crawler?useUnicode=true&characterEncoding=UTF-8";
		Connection conn = JDBCUtils.connect(DATABASE_ADDRESS, DATABASE_USER_NAME, DATABASE_PASSWORD);
		Statement state = conn.createStatement();
		ResultSet res = state.executeQuery("CALL GET_URL()");
//		state.execute("insert into urlList(pk_url,state) values('正大光明','1')");
		
		if(res.next())
		System.out.println(res.getString("URL"));
		
	}

	@SuppressWarnings("resource")
	@Override
	public String geturl() {
		// TODO Auto-generated method stub
		Connection conn = connectionPool.pop();
		if(conn == null)
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		try {
			while(conn.isClosed())conn = JDBCUtils.connect(DATABASE_ADDRESS, DATABASE_USER_NAME, DATABASE_PASSWORD);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("conn closed");
			connectionPool.push(conn);
			return null;
		}
		Statement state = null;
		try {
			state = conn.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println("statement error");
			connectionPool.push(conn);
			return null;
		}
		ResultSet res = null;
		try {
			res = state.executeQuery("CALL GET_URL()");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			connectionPool.push(conn);
			System.out.println("function error");
			return null;
		}
		connectionPool.push(conn);
		String ret = null;
		try {
			if(res.next())
			ret= res.getString("URL");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("nothing to return");
			connectionPool.push(conn);
			return null;
		}
		connectionPool.push(conn);
		System.out.println(ret);
		return ret;
	}

	@Override
	public void save(String url, String source) {
		// TODO Auto-generated method stub
		Connection conn = connectionPool.pop();
		if(conn == null)
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				connectionPool.push(conn);
				e.printStackTrace();
			}
			connectionPool.push(conn);
			return ;
		}
		try {
			while(conn.isClosed())conn = JDBCUtils.connect(DATABASE_ADDRESS, DATABASE_USER_NAME, DATABASE_PASSWORD);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			connectionPool.push(conn);
			return ;
		}
		PreparedStatement  state = null;
		try {
			state = conn.prepareStatement("CALL SAVE_RESULT(?,?)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			connectionPool.push(conn);
			return ;
		}
		try {
			state.setString(1,url);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			connectionPool.push(conn);
			return ;
		}
		try {
			state.setString(2, source);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			connectionPool.push(conn);
			return ;
		}
		try {
			state.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			connectionPool.push(conn);
			return;
		}connectionPool.push(conn);
	}

	@Override
	public void save(Page p) {
		// TODO Auto-generated method stub
		save(p.getUrl(),p.getSource());
	}
	


}
