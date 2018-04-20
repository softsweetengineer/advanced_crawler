package control;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import interfaces.Communication;
import util.selenium.SeleniumPool;
import util.selenium.SeleniumSetting;

/**
 * 
 * @author Tank
 * @version 2018年3月23日
 * 启动爬虫的入口，主要负责按照顺序启动若干线程
 */

public class Main {

	static {System.out.println("loading main");}

	public static Communication communication=new Communicator_30();
	public static int NUMBER_OF_CATCHER=1;
	public static int NUMBER_OF_CRAWLER=4;
	public static int NUMBER_OF_ANALYZER=1;
	public static int NUMBER_OF_UPDATER=1;
	public static String NAME_OF_CATCHER="control.Catcher_30";
	public static String NAME_OF_CRAWLER="control.Crawler_30";
	public static String NAME_OF_ANALYZER="control.Analyzer_30";
	public static int SELENIUM_POOL_SIZE=3;
	public static SeleniumPool seleniumPool = null;
	
	public static Runnable getThread (String className)
	{
		Runnable ret = null;
		try {
			ret= (Runnable) Class.forName(className).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			return null;
		}
		return ret;
	}
	/**
	 * 从配置文件中读取配置信息。
	 */
	public static void config()
	{
		String className = null;
		Integer number =null;
		
		className = Config.getString("NAME_OF_CATCHER");
		if(className != null)NAME_OF_CATCHER=className;
		className = Config.getString("NAME_OF_CRAWLER");
		if(className != null)NAME_OF_CRAWLER = className;
		className = Config.getString("NAME_OF_ANALYZER");
		if(className != null)NAME_OF_ANALYZER = className;
		number = Config.getInteger("NUMBER_OF_CATCHER");
		if(number != null)NUMBER_OF_CATCHER=number;
		number = Config.getInteger("NUMBER_OF_CRAWLER");
		if(number!=null)NUMBER_OF_CRAWLER=number;
		number = Config.getInteger("NUMBER_OF_ANALYZER");
		if(number != null)NUMBER_OF_ANALYZER=number;
		number = Config.getInteger("NUMBER_OF_UPDATER");
		if(number != null)NUMBER_OF_UPDATER=number;
		number = Config.getInteger("SELENIUM_POOL_SIZE");
		if(number != null)SELENIUM_POOL_SIZE=number;
		
	}
	
	/**
	 * 进行初始化操作
	 */
	public static void init()
	{
		System.setProperty("webdriver.firefox.bin", SeleniumSetting.firfoxPath);
		Config.init();
		config();
		MapReader.readMap();
		seleniumPool = new SeleniumPool(SELENIUM_POOL_SIZE);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		init();
		ExecutorService pool = Executors.newCachedThreadPool();	
		
		for(int i=0;i<NUMBER_OF_CATCHER;++i)
			pool.execute(getThread(NAME_OF_CATCHER));
		for(int i=0;i<NUMBER_OF_CRAWLER;++i)
			pool.execute(getThread(NAME_OF_CRAWLER));
		for(int i=0;i<NUMBER_OF_ANALYZER;++i)
			pool.execute(getThread(NAME_OF_ANALYZER));
		
		while(true)
		{
			System.out.println(Main.communication.urlSize());
			System.out.println(Main.communication.sourceSize());
			System.out.println(Main.communication.resultSize());
			System.out.println("pool:　"+pool);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
