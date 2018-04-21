package control;

import bean.Page;
import interfaces.DataBase;

public class Updater_30 implements Runnable{

	public static int UPDATER_EMPTY_DELAY=1000;
	public static int UPDATER_NORMAL_DELAY=100;
	public static  String UPDATER_DBC_NAME="database.SingleTestDBC";
	
	
	
	public static void config()
	{
		String className = null;
		Integer number = null;
		number = Config.getInteger("UPDATER_EMPTY_DELAY");
		if(number != null)UPDATER_EMPTY_DELAY = number;
		number = Config.getInteger("UPDATER_NORMAL_DELAY");
		if(number != null)UPDATER_NORMAL_DELAY=number;
		className = Config.getString("UPDATER_DBC_NAME");
		if(className != null)UPDATER_DBC_NAME=className;
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		config();
		DataBase DBC = getDBC(UPDATER_DBC_NAME);
		Page page = Main.communication.getResult();
		while(true)
		{
				System.out.println(DBC.getClass().getName()+"--------UPDATER IS RUNNING");
			if(page==null)	
				try {
					Thread.sleep(UPDATER_EMPTY_DELAY);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			
			else DBC.save(page);
			System.out.println(DBC.getClass().getName());
			page = Main.communication.getResult();
			
			try {
				Thread.sleep(UPDATER_NORMAL_DELAY);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
