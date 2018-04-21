package control;

import bean.Page;
import crawler.Crawler_DEFAULT;
import interfaces.Crawler;

/**
 * 使用工厂模式来任意的声明针对不同网站的URL
 * @author Tank
 * @version 2018年3月23日
 * 
 * 
 */
public class Crawler_30 implements Runnable {


	public static int CRAWLER_EMPTY_DELAY=1000;
	public static int CRAWLER_NORMAL_DELAY=100;
	public static String DEFAULT_CRAWLER_NAME = "crawler.Crawler_DEFAULT";
	
	/**
	 * 使用配置文件中有的信息替换默认配置信息。
	 */
	public static void Config()
	{
		String className = null;
		Integer number = null;
		number = Config.getInteger("CRAWLER_EMPTY_DELAY");
		if(number != null)CRAWLER_EMPTY_DELAY = number;
		number = Config.getInteger("CRAWLER_NORMAL_DELAY");
		if(number != null)CRAWLER_NORMAL_DELAY=number;
		className = Config.getString("DEFAULT_CRAWLER_NAME");
		if(className != null)DEFAULT_CRAWLER_NAME=className;

	}


	public static Crawler getCrawler(String className) 
	{
		Crawler ret = null;
		try {
			ret = (Crawler) Class.forName(className).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			
			ret = new Crawler_DEFAULT();
			return ret;
		}
		return ret;
	}
	
	
	public void old_run() {
		// TODO Auto-generated method stub
		Crawler crawler= getCrawler(DEFAULT_CRAWLER_NAME);
		String url = null;
		while(true)
		{
			url = Main.communication.geturl();
			if(url == null)
			{
				try {
					Thread.sleep(CRAWLER_EMPTY_DELAY);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else
			{
				Main.communication.putSource(crawler.getPage(url));
			}
			try {
				Thread.sleep(CRAWLER_NORMAL_DELAY);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		Crawler crawler = null;
		String url = null;
		while(true)
		{
			url = Main.communication.geturl();
			if(url == null)
			{
				try {
					Thread.sleep(CRAWLER_EMPTY_DELAY);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				
				}
			}else
			{
				String className = MapReader.getCrawlerName(url);
				if(className == null)className = DEFAULT_CRAWLER_NAME;
				crawler = getCrawler(className);
				Page tar = crawler.getPage(url);
				Main.communication.putSource(tar);
			}
			try {
				Thread.sleep(CRAWLER_NORMAL_DELAY);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
