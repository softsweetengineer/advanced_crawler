package control;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import crawler.Crawler_DEFAULT;
import interfaces.Crawler;
import util.selenium.SeleniumSetting;

public class Test {
	public static void add(Integer n)
	{
		n++;
	}
	public static void main(String[] args) {
		System.setProperty("webdriver.firefox.bin", SeleniumSetting.firfoxPath);
		
//		WebDriver web = new FirefoxDriver();
//		web.get("http://www.runoob.com/java/java-multithreading.html");
//		System.out.println(web.getPageSource());
		
		Crawler crawler = new Crawler_DEFAULT();
		
		System.out.println(crawler.getPage("http://www.runoob.com/java/java-multithreading.html").getSource());
	}

}
