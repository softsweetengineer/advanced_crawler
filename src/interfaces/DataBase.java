package interfaces;

import bean.Page;

/**
 * 
 * @author Tank
 * @version 2018年4月9日
 *  DataBase接口用于规范终端系统和数据库之间的交互。提供两个函数，geturl()和save(),分别代表从标准数据库中获取url数据和将爬取到的内容存入数据库中。
 */
public interface DataBase {

	
	/**
	 * 返回一个数据库中待抓取的url同时重置该url状态为待抓取
	 * @return 一个String对象代表一个待抓取的url
	 */
	public String geturl();
	
	/**
	 * 将一组处理后的信息重新存进数据库。
	 * @param url
	 * @param source
	 */
	public void save(String url,String source);
	
	/**
	 * 接受一个Page类的实例，并将其储存在数据库中
	 * @param p
	 */
	public void save(Page p);
	
	/**
	 * 重写以提供从Config中读取配置的代码。
	 */
	public static void config() {
	}
}
