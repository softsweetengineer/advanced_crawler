package util.selenium;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * 实现一个Selenium池，避免每次进行网页抓取时重新建立并销毁浏览器。
 * @author Tank
 * @version 2018年4月20日
 */
public class SeleniumPool {

	private int size = 3;
	private List<WebDriver> stack = new ArrayList<WebDriver>(); 
	
	public synchronized WebDriver pop()
	{
		WebDriver ret = null;
		if(stack.isEmpty())return ret;
		ret = stack.get(0);
		stack.remove(0);
		return ret;
	}
	public synchronized void push(WebDriver web)
	{
		stack.add(web);
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		
		this.size = size;
	}

	public void init()
	{
		stack.clear();
		for(int i=0;i<size;++i)
		{
			WebDriver web = new FirefoxDriver();
			stack.add(web);
		}
	}
	public SeleniumPool(){init();}
	public SeleniumPool(int size)
	 {
		 setSize(size);
		 init();
	 }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
