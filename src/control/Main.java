package control;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import interfaces.Communication;

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
	public static int NUMBER_OF_CRAWLER=1;
	public static int NUMBER_OF_ANALYZER=1;
	public static int NUMBER_OF_UPDATER=1;
	public static String NAME_OF_CATCHER="control.Catcher_30";
	public static String NAME_OF_CRAWLER="control.Crawler_30";
	public static String NAME_OF_ANALYZER="control.Analyzer_30";
	
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			ExecutorService pool = Executors.newCachedThreadPool();	
		
		for(int i=0;i<NUMBER_OF_CATCHER;++i)
			pool.execute(getThread(NAME_OF_CATCHER));
		for(int i=0;i<NUMBER_OF_CRAWLER;++i)
			pool.execute(getThread(NAME_OF_CRAWLER));
		for(int i=0;i<NUMBER_OF_ANALYZER;++i)
			pool.execute(getThread(NAME_OF_ANALYZER));
		
		for(int i=0;i<233;++i)
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
