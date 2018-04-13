package control;

import interfaces.DataBase;
/**
 * 
 * @author Tank
 * @version 2018年3月23日
 *  主要爬虫模块
 */
public class Catcher_30 implements Runnable {

	
	public static int EMPTY_DELAY=100000;
	public static int NORMAL_DELAY=100;
	public  String DBC_NAME="database.SingleTestDBC";
	 
	
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
					Thread.sleep(EMPTY_DELAY);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			
			else Main.communication.puturl(url);
			
			try {
				Thread.sleep(NORMAL_DELAY);
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
