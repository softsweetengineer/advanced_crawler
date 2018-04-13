package database;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import bean.Page;
import interfaces.DataBase;

public class SingleTestDBC implements DataBase {

	ArrayList<String> urls=new ArrayList<String>();
	Map<String,String> result=new TreeMap<String,String>();
	
	public SingleTestDBC()
	{
		String base="http://acm.hnu.cn/online/?action=problem&type=show&id=";
		for(Integer i=10000;i<=14092;++i)
		{
			urls.add(base+i.toString());
		}
	}
	
	
	@Override
	public synchronized  String geturl() {
		// TODO Auto-generated method stub
		if(urls.isEmpty())return null;
		String ret=urls.get(0);
		urls.remove(0);
		return ret;
	}
	
	

	@Override
	public synchronized  void save(String url, String source) {
		// TODO Auto-generated method stub
		result.put(url,source);
	}


	@Override
	public void save(Page p) {
		// TODO Auto-generated method stub
		
	}

}
