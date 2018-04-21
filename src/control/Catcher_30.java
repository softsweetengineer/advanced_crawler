package control;

import interfaces.DataBase;
/**
 * 主要url数据获取模块,负责多线程的从数Database实例中取URL信息。
 * @author Tank
 * @version 2018年3月23日
 * 
 */
public class Catcher_30 implements Runnable {

	
	public static int CATCHER_EMPTY_DELAY=100000;
	public static int CATCHER_NORMAL_DELAY=100;
	public static  String DBC_NAME="database.SingleTestDBC";
	
	/**
	 * 使用配置文件中有的信息替换默认配置信息。
	 */
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
