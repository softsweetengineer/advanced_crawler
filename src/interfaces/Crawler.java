package interfaces;

import bean.Page;
/**
 * Crawler这个interface规约实现一个函数，getPage(),该函数负责将url对应的网页转化成Page类实例。
 * 
 * @author Tank
 * @version 2018年4月9日
 */
public interface Crawler {
	/**
	 * 用于表示是否需要运行config函数进行初始化
	 */
	static boolean needConfig = true;
	
	/**
	 * 定义该函数的主要目标是得到url对应的原始源代码，后将原始结果封装为一个Page类。
	 * @param url
	 * @return 通过抓取网页html代码得到的原始Page实例
	 */
	public Page getPage(String url);
	
	/**
	 * 重写以提供从Config中读取配置的代码。
	 */
	public static void config() {
	}
}
