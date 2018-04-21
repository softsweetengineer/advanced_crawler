package interfaces;

import bean.Page;
/**
 * 
 * @author Tank
 * @version 2018年4月9日
 * Analyzer 接口将要求程序实现一个函数，analyze(),该函数将会分析页面，并将分析结果打包进Page类中。
 * 
 */
public interface Analyzer {
	/**
	 * 用于表示是否需要运行config函数进行初始化
	 */
	static boolean needConfig = true;
	
	/**
	 * @param p
	 * @return 分析后的页面信息
	 */
	public Page analyze(Page p);
	
	/**
	 * 重写以提供从Config中读取配置的代码。
	 */
	public static void config() {
	}
	
}
