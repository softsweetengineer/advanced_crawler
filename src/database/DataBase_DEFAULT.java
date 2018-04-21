package database;

import bean.Page;
import interfaces.DataBase;

public class DataBase_DEFAULT implements DataBase{

	
	public static String DATABASE_ADDRESS = "localhost";
	public static String DATABASE_USER_NAME = "root";
	public static String DATABASE_PASSWORD = "passWORD,233";
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public String geturl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(String url, String source) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(Page p) {
		// TODO Auto-generated method stub
		
	}

	

}
