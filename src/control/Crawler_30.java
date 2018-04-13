package control;

import bean.Page;
import interfaces.Crawler;

/**
 * 使用工厂模式来任意的声明针对不同网站的URL
 * @author Tank
 * @version 2018年3月23日
 * 
 * 
 */
public class Crawler_30 implements Runnable {


	public int EMPTY_DELAY=1000;
	public int NORMAL_DELAY=100;
	public static String CRAWLER_NAME="crawler.Crawler_HOJ";
	public static String DEFAULT_CRAWLER_NAME = "crawler.Crawler_DEFAULT";
	
	
	public int getEMPTY_DELAY() {
		return EMPTY_DELAY;
	}


	public void setEMPTY_DELAY(int eMPTY_DELAY) {
		EMPTY_DELAY = eMPTY_DELAY;
	}


	public int getNORMAL_DELAY() {
		return NORMAL_DELAY;
	}


	public void setNORMAL_DELAY(int nORMAL_DELAY) {
		NORMAL_DELAY = nORMAL_DELAY;
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
			return null;
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
					Thread.sleep(EMPTY_DELAY);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else
			{
				Main.communication.putSource(crawler.getPage(url));
			}
			try {
				Thread.sleep(NORMAL_DELAY);
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
					Thread.sleep(EMPTY_DELAY);
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
				Thread.sleep(NORMAL_DELAY);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
