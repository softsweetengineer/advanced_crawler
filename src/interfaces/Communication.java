package interfaces;

import bean.Page;
/**
 * Communication interface定义类一组用于线程间通信的函数，用于实现线程间通信
 * @author Tank
 * @version 2018年4月9日
 */
public interface Communication {

	
	/**
	 * 从url队列中获取一个url，并将其从队列中移除。
	 * @return 一个String实例代表了一个待抓取的url
	 */
	public String geturl();
	
	/**
	 * 将url放进url队列。
	 * @param url
	 */
	public void puturl(String url);
	
	/**
	 * 将一个原始的Page实例放入待分析的源代码队列。
	 * @param p
	 */
	public void putSource(Page p);
	
	/**
	 * 从待分析队列中获取一个Page实例，并将其从队列中移除。
	 * @return 待分析的Page实例
	 */
	public Page getSource();
	
	/**
	 * 将一个完成分析的Page实例放入结果队列。
	 * @param p
	 */
	public void putResult(Page p);
	
	/**
	 * 从完成分析的结果队列中得到一个Page实例，并将其从队列中移除。
	 * @return 完成分析的Page实例
	 */
	public Page getResult();
	
	
	/**
	 * 得到url队列的尺寸。
	 * @return url队列的尺寸
	 */
	public int urlSize();
	
	/**
	 * 得到待分析队列的尺寸。
	 * @return source队列的尺寸
	 */
	public int sourceSize();
	
	/**
	 * 得到结果队列的尺寸。
	 * @return result队列的尺寸
	 */
	public int resultSize();
	
	
	public static void config()
	{
		
	}
}
