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


	public int CRAWLER_EMPTY_DELAY=1000;
	public int CRAWLER_NORMAL_DELAY=100;
	public static String CRAWLER_NAME="crawler.Crawler_HOJ";
	public static String DEFAULT_CRAWLER_NAME = "crawler.Crawler_DEFAULT";
	
	
	public int getCRAWLER_EMPTY_DELAY() {
		return CRAWLER_EMPTY_DELAY;
	}


	public void setCRAWLER_EMPTY_DELAY(int CRAWLER_EMPTY_DELAY) {
		this.CRAWLER_EMPTY_DELAY = CRAWLER_EMPTY_DELAY;
	}


	public int getCRAWLER_NORMAL_DELAY() {
		return CRAWLER_NORMAL_DELAY;
	}


	public void setCRAWLER_NORMAL_DELAY(int CRAWLER_NORMAL_DELAY) {
		this.CRAWLER_NORMAL_DELAY = CRAWLER_NORMAL_DELAY;
	}


	public static String getCRAWLER_NAME() {
		return CRAWLER_NAME;
	}


	public static void setCRAWLER_NAME(String cRAWLER_NAME) {
		CRAWLER_NAME = cRAWLER_NAME;
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
		Crawler crawler= getCrawler(CRAWLER_NAME);
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
