package control;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;
import java.util.TreeMap;

/**
 * 将会读取并保存所有可配资参数的映射。在程序开始后由各个组件自行查找。
 * @author Tank
 * @version 2018年4月21日
 */
public class Config {

	
	public static Map<String,String>mappString = new TreeMap<String,String>();
	public static Map<String,Integer>mappInteger = new TreeMap<String,Integer>();
	public static String filePath="src//config.cfg";
	public static String STOP[]= {"CRAWLER_CONFIG:","ANALYZER_CONFIG:"};
	public static String START = "CONFIG:";
	
	
	public static boolean isStop(String str)
	{
		int len = STOP.length;
		for(int i=0;i<len;++i)
		{
			if(str.indexOf(STOP[i])!=-1)return true;
		}
		return false;
	}
	public static boolean isStart(String str)
	{
		if(str.indexOf(START)!=-1)return true;
		return false;
	}
	public static boolean isNumber(String str)
	{
		int len =str.length();
		for(int i=0;i<len;++i)
		{
			if(str.charAt(i)>'9'||str.charAt(i)<'0')return false;
		}return true;
	}
	
	public static void init()
	{
		String address = "config.cfg";
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
		boolean flag = false;

		while(true)
		{
			try {
				str = cin.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(str == null)break;
					
			if(isStop(str))
			{
				flag = false;
				continue;
			}
			if(isStart(str))
			{
				flag = true;
				continue;
			}
			if(flag)
			{
				String map[]=str.split(":=");
				if(map.length!=2)continue;
				System.out.println(map[0]+":="+map[1]);
				if(isNumber(map[1]))mappInteger.put(map[0],new Integer(map[1]));
				else mappString.put(map[0], map[1]);
			}
		}
	}
	
	public static String getString(String str)
	{
		if(mappString.containsKey(str))return mappString.get(str);
		return null;
	}
	
	public static Integer getInteger(String str)
	{
		if(mappInteger.containsKey(str))return mappInteger.get(str);
		return null;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
