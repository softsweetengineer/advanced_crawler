package util.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 实现了数据库连接池。避免重复链接数据库造成的效率问题。
 * @author Tank
 * @version 2018年4月21日
 */
public class ConnectionPool {

	private int size = 3;
	private List<Connection> stack = new ArrayList<Connection>(); 
	
	public synchronized Connection pop()
	{
		Connection ret = null;
		if(stack.isEmpty())return ret;
		ret = stack.get(0);
		stack.remove(0);
		
		
		return ret;
	}
	
	public synchronized void push(Connection web)
	{
		stack.add(web);
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		
		this.size = size;
	}

	public void init(String address,String userName,String password)
	{
		stack.clear();
		for(int i=0;i<size;++i)
		{
			Connection conn;
			try {
				conn = JDBCUtils.connect(address, userName, password);
			} catch (ClassNotFoundException | SQLException e) {
				return;
			}
			stack.add(conn);
		}
	}
	
	public ConnectionPool(String address,String userName,String password,int size)
	{
		setSize(size);
		init(address,userName,password);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
