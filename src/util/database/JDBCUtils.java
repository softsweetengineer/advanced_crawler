package util.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



/**
 * 
 * @author Tank
 * @version 2017-11-18
 * @see 数据库操作的基本类工具，将会具有包括链接、增加、修改、查询、删除操作在内的静态方法。
 */
public class JDBCUtils {
	
	/**
	 * @see 用于链接数据库，不接受参数，从系统变量中读取相关参数。
	 * @return 数据库链接后的实例
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * 
	 */
	public static Connection connect(String address,String userName,String password) throws ClassNotFoundException, SQLException
	{
		//long begin  = System.currentTimeMillis();
		Class.forName("com.mysql.jdbc.Driver");

		System.out.println("Connecting to database...");
		Connection conn = DriverManager.getConnection(address,userName,password);

		//long end  = System.currentTimeMillis();
		//System.out.println(end-begin+"ms");
		
		return conn;
	}
	/**
	 * 数据库查询语句，将会返回结果集合
	 * @param conn 为数据库链接实例（Connection）
	 * @param tableName 待查询的表名称
	 * @return ResultSet 返回该表的所有内容
	 * @throws SQLException
	 */
	public static Integer countByKey(Connection conn,String tableName,String keyList,String valueList) throws SQLException
	{
		Integer cnt=0;
		
		String sql="SELECT COUNT(*) FROM "+tableName+" WHERE ";		
		Statement sta=conn.createStatement();
		String keys[]=keyList.split(",");
		String values[]=valueList.split(",");
		int len=keys.length;
		sql+= keys[0] +" LIKE "+ " '%" + values[0]+"%' ";
		for(int i=1;i<len;++i)
		{
			sql+=" AND "+keys[i]+" LIKE '%"+values[i]+"%' ";
		}
		sql+=";";
		System.out.println(sql);
		ResultSet rs = sta.executeQuery(sql);
		if(rs.next())cnt=rs.getInt(1);
		return cnt;
	}
	public static Integer countByKey_eq(Connection conn,String tableName,String keyList,String valueList) throws SQLException
	{
		Integer cnt=0;
		
		String sql="SELECT COUNT(*) FROM "+tableName+" WHERE ";		
		Statement sta=conn.createStatement();
		String keys[]=keyList.split(",");
		String values[]=valueList.split(",");
		int len=keys.length;
		sql+= keys[0] +" = "+ " '" + values[0]+"' ";
		for(int i=1;i<len;++i)
		{
			sql+=" AND "+keys[i]+" = '"+values[i]+"' ";
		}
		sql+=";";
		System.out.println(sql);
		ResultSet rs = sta.executeQuery(sql);
		if(rs.next())cnt=rs.getInt(1);
		return cnt;
	}
	public static ResultSet queryAll(Connection conn,String tableName) throws SQLException
	{
		String sql="SELECT * FROM "+tableName+";";
		Statement sta=conn.createStatement();
		ResultSet rs = sta.executeQuery(sql);
		return rs;
	}
	
	public static Integer countDistinct(Connection conn,String tableName,String distinctKey,String keyList,String valueList) throws SQLException
	{
		Integer cnt=0;
		Statement sta=conn.createStatement();
		String sql="SELECT COUNT(*) FROM ( SELECT DISTINCT "+ distinctKey + " FROM "+tableName +" WHERE ";
		String keys[]=keyList.split(",");
		String values[]=valueList.split(",");
		int len=keys.length;
		sql+= keys[0] +" = "+ " '" + values[0]+"' ";
		for(int i=1;i<len;++i)
		{
			sql+=" AND "+keys[i]+" = '"+values[i]+"' ";
		}
		sql+=" ) AS TOTAL;";
		System.out.println(sql);
		ResultSet res = sta.executeQuery(sql);
		if(res.next())cnt=res.getInt(1);
		return cnt;
	}
	
	public static Integer countAll(Connection conn,String tableName) throws SQLException
	{
		String sql="SELECT COUNT(*) FROM "+tableName+" ;";
		Statement sta=conn.createStatement();
		ResultSet rs = sta.executeQuery(sql);
		Integer cnt=0;
		if(rs.next())cnt=rs.getInt(1);
		return cnt;
	}
	
	/**
	 * @see 使用AND链接所有条件并将结果返回
	 * @param conn
	 * @param tableName
	 * @param newKeys
	 * @param newValues
	 * @return ResultSet
	 * @throws SQLException
	 */
	//ResultSet res=queryOneAnd(conn,tablename,ID,"'"+id.toString+"'");
	public static ResultSet queryOneAnd(Connection conn,String tableName,String newKeys,String newValues) throws SQLException
	{
		String sql="select * FROM "+tableName+" WHERE ";
		String []keys=newKeys.split(",");
		String []values=newValues.split(",");
		sql+=keys[0]+" = '"+values[0]+"' ";
		int len=keys.length;
		for(int i=1;i<len;++i)
		{
			sql+=" AND "+keys[i]+" = '"+values[i]+"' ";
		}
		sql+=";";
		System.out.println(sql);
		Statement sta=conn.createStatement();
		ResultSet rs = sta.executeQuery(sql);
			
		return rs;
		
		
	}
	
	/**
	 * @see 数据库插入语句，将在指定表中，按照给定顺序插入给定序列的值。
	 * @param conn
	 * @param tableName
	 * @param keys
	 * @param values
	 * @return
	 * @throws SQLException
	 */
	public static int insert(Connection conn,String tableName,String newKeys,String newValues) throws SQLException
	{
//		String sql="INSERT INTO "+tableName+" ("+keys+") VALUES("+values+");";
		String sql="INSERT INTO "+tableName+" (";
		String []keys=newKeys.split(",");
		String []values=newValues.split(",");
		sql+=keys[0];//+" = '"+values[0]+"' ";
		int len=keys.length;
		for(int i=1;i<len;++i)
		{
			sql+=" , "+keys[i];
		}sql+=") VALUES (";
		sql+="'"+values[0]+"'";
		len=values.length;
		for(int i=1;i<len;++i)
		{
			sql+=" , '"+values[i]+"'";
		}sql+=");";
		
		System.out.println(sql);
		Statement sta=conn.createStatement();
		 sta.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
		
		ResultSet rs= sta.getGeneratedKeys ();
		if(rs.next())
		return rs.getInt(1);
		return -1;
	//	 rs;
	}
	/**
	 * @see 使用AND来链接各个条件进行更新
	 * @param conn
	 * @param tableName
	 * @param newKeys
	 * @param newValues
	 * @param oldKeys
	 * @param oldValues
	 * @return
	 * @throws SQLException 
	 */
	public static int changeAnd(Connection conn,String tableName,String newKeys,String newValues,String oldKeys,String oldValues) throws SQLException
	{
		String sql="UPDATE "+tableName+" SET ";
		String keys[]=newKeys.split(",");
		String values[]=newValues.split(",");
		sql+=keys[0]+" = '"+values[0]+"' ";
		int len=keys.length;
		for(int i=1;i<len;++i)
		{
			sql+=", "+keys[i]+" = '"+values[i]+"' ";
		}
		sql+=" WHERE ";
		keys=oldKeys.split(",");
		values=oldValues.split(",");
		sql+=keys[0]+" = '"+values[0]+"' ";
		len=keys.length;
		for(int i=1;i<len;++i)
		{
			sql+=" AND "+keys[i]+" = '"+values[i]+"' ";
		}
		sql+=";";
		System.out.println(sql);
		Statement sta=conn.createStatement();
		int rs = sta.executeUpdate(sql);
		return rs;
	}
	
	public static Integer countBetween(Connection conn,String tableName,String key,Integer a,Integer b) throws SQLException
	{
		Integer tmp=Math.min(a, b);
		b=Math.max(a, b);
		a=tmp;
				
		String sql = "SELECT COUNT(*) FROM "+ tableName + " WHERE " + key + " >= " + a.toString() + " AND "+
		key + " <= "+b.toString()+" ; ";
		System.out.println(sql);
		Statement sta=conn.createStatement();
		ResultSet res=sta.executeQuery(sql);
		if(res.next())return res.getInt(1);
		else return 0;
		
	}
	
	public static boolean delete(Connection conn,String table,String keyList,String valueList) throws SQLException
	{
		String sql="DELETE FROM "+table+" WHERE ";
		String keys[]=keyList.split(",");
		String values[]=valueList.split(",");
		sql+=keys[0]+" = '"+values[0]+"' ";
		int len=keys.length;
		for(int i=1;i<len;++i)
		{
			sql+="AND "+keys[i]+" = '"+values[i]+"' ";
		}
		System.out.println(sql);
		Statement sta=conn.createStatement();
		boolean rs=sta.execute(sql);
		return rs;
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

}
