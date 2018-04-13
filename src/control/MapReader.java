package control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/**
 * 
 * @author Tank
 * @version 2018年4月7日
 * 静态类，主要用于维护url关键字和对应的Crawler、Analyzer类之间的对应关系。
 * 
 * 
 */
public class MapReader {

	public static Map<String,String> crawlerMap=new TreeMap<String,String>();
	public static List<String> crawlerList=new ArrayList<String>();
	public static Map<String,String> analyzerMap = new TreeMap<String,String>();
	public static List<String> analyzerList = new ArrayList<String>();
	public static Map<String,String> pageMap = new TreeMap<String,String>();
	public static List<String> pageList = new ArrayList<String>();
	
	public static void testReadMap(String address)
	{
		File file = new File(address);
		if(!file.exists())
		{
			System.out.println("文件不存在");
		}

		Reader fileReader=null;

			try {
				fileReader = new FileReader(address);
			} catch (FileNotFoundException e) {
				return;
			}
		@SuppressWarnings("resource")
		BufferedReader cin = new BufferedReader(fileReader);
		crawlerMap.clear();
		crawlerList.clear();		
		String str = null;
		while(true)
		{
			try {
				str = cin.readLine();
			} catch (IOException e) {
				break;
			}
			if(str==null)break;
			if(!str.contains("="))break;
			String mapp[] = str.split("=");
			crawlerMap.put(mapp[0], mapp[1]);
			crawlerList.add(mapp[0]);
			System.out.println(mapp[0]+" : "+mapp[1]);
		}
		
	}
	
	/**
	 * 从配置文件中读取第一个适配的关键字，并针对该关键字找到完整Crawler具体类名。
	 * @param url
	 * @return 在映射关系中找得到的Crawler类的完整名称
	 */
	public static String getCrawlerName (String url)
	{
		String ret = null;
		
		int len=MapReader.crawlerList.size();
		for(int i=0;i<len;++i)
		{
			if(url.indexOf(crawlerList.get(i))!=-1)
			{
				String first = crawlerList.get(i);
				ret = crawlerMap.get(first);
				break;
			}
		}
		return ret;
	}
	
	/**
	 * 从配置文件中读取第一个适配的关键字，并针对该关键字找到完整Crawler具体类名。
	 * @param url
	 * @return 在映射关系中找得到的Analyzer具体类的完整名称
	 */
	public static String getAnalyzerName(String url)
	{
		String ret = null;
		
		int len=MapReader.analyzerList.size();
		for(int i=0;i<len;++i)
		{
			if(url.indexOf(analyzerList.get(i))!=-1)
			{
				String first = analyzerList.get(i);
				ret = analyzerMap.get(first);
				break;
			}
		}
		return ret;
	}
	/**
	 * 从配置文件中读取第一个适配的关键字，并针对该关键字找到完整Page具体类名。
	 * @param url
	 * @return 在映射关系中找得到的Analyzer具体类的完整名称
	 */
	public static String getPageName(String url)
	{
		String ret = null;
		int len=MapReader.pageList.size();
		for(int i=0;i<len;++i)
		{
			if(url.indexOf(pageList.get(i))!=-1)
			{
				String first = pageList.get(i);
				ret = pageMap.get(first);
				break;
			}
		}
		
		return ret;
	}
	
	/**
	 * @see 在指定地址读取配置文件并用于初始化关键字和类之间的映射关系
	 * @param address
	 */
	public static void readMap(String address)
	{
		Reader fileReader = null;
		try {
			fileReader = new FileReader(address);
		} catch (FileNotFoundException e) {
			System.out.println("文件不存在");
			return ;
		}
		@SuppressWarnings("resource")
		BufferedReader cin = new BufferedReader(fileReader);
		String str = null;
		Map<String, String> mapNow = null;
		List<String> listNow = null;
		while(true)
		{
			try {
				str = cin.readLine();
			} catch (IOException e) {
				System.out.println("文件读取出错");
				return;
			}
			if(str == null)break;
			if(str.contains("CRAWLER_CONFIG:"))
			{
				mapNow = MapReader.crawlerMap;
				listNow = MapReader.crawlerList;
				System.out.println("读取ULR映射:");
				continue;
			}
			if(str.contains("ANALYZER_CONFIG:"))
			{
				mapNow = MapReader.analyzerMap;
				listNow = MapReader.analyzerList;
				System.out.println("读取Analyzer映射:");
				continue;
			}
			if(str.contains("PAGE_CONFIG:"))
			{
				mapNow = MapReader.pageMap;
				listNow = MapReader.pageList;
				System.out.println("读取page映射:");
			}
			if(mapNow == null|| listNow == null)continue;
			String mapp[] = str.split("=");
			crawlerMap.put(mapp[0], mapp[1]);
			crawlerList.add(mapp[0]);
				System.out.println(mapp[0]+" : "+mapp[1]);	
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		readMap("src//config_test.cfg");
	}

}
