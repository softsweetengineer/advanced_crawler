package util.selenium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;



/**
 * 
 * @author Tank 2017-7-16
 * @see 方法类，用于定义下载网页源代码的方法
 * @version 0.0.0
 */
public class WebSourceDownloadUtil {
	/**
	 * 
	 * @author Tank 2017-7-16
	 * @see 使用JAVA自带的URL来下载对应URL的网页源代码
	 * @version 0.0.0
	 * @return String类型的返回网页源代码
	 */
	public static String downloadPageByNet(String address) throws IOException
	{
		URL page =new URL(address);
		InputStream stream = page.openStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream,"UTF-8"));
		String line;
		StringBuilder pageBuffer = new StringBuilder(1000);
		while((line=reader.readLine())!= null)
		{
			pageBuffer.append("\n");
			pageBuffer.append(line);
		}
		return pageBuffer.toString();
	}
	
	/**
	 * 使用selenium和firefoxDriver来一次性下载对应URL的网页源代码
	 * @author Tank 2017-7-16
	 * @version 0.0.0
	 * @return String类型的返回网页源代码
	 */
	public static String downloadPageByFirefox(String address) 
	{
		System.setProperty("webdriver.firefox.bin", SeleniumSetting.firfoxPath);
		WebDriver driver=new FirefoxDriver();
		driver.get(address);
		
		String ret= driver.getPageSource();
		driver.close();
		return ret;
	}

	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
			System.out.println(downloadPageByFirefox("http://www.ysba.cc/Movies/36666.html").equals(downloadPageByNet("http://www.ysba.cc/Movies/36666.html")));
	}

}
