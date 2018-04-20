package control;

import interfaces.DataBase;
/**
 * 
 * @author Tank
 * @version 2018年3月23日
 *  主要url数据获取模块
 */
public class Catcher_30 implements Runnable {

	
	public static int CATCHER_EMPTY_DELAY=100000;
	public static int CATCHER_NORMAL_DELAY=100;
	public static  String DBC_NAME="database.SingleTestDBC";
	 
	public static void Config()
	{
		String className = null;
		Integer number = null;
		number = Config.getInteger("CATCHER_EMPTY_DELAY");
		if(number != null)CATCHER_EMPTY_DELAY = number;
		number = Config.getInteger("CATCHER_NORMAL_DELAY");
		if(number != null)CATCHER_NORMAL_DELAY=number;
		className = Config.getString("DBC_NAME");
		if(className != null)DBC_NAME=className;
		
		
	}
	
	
	public static DataBase getDBC(String className)
	{
		DataBase ret=null;
		try {
			ret = (DataBase) Class.forName(className).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			return null;
		}
		return ret;
		
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		DataBase DBC = getDBC(DBC_NAME);
		String url=DBC.geturl();
		while(true)
		{
			if(url==null)
				try {
					Thread.sleep(CATCHER_EMPTY_DELAY);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			
			else Main.communication.puturl(url);
			url = DBC.geturl();
			try {
				Thread.sleep(CATCHER_NORMAL_DELAY);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
