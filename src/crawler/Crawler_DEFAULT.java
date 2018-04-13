package crawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

import bean.Page;
import bean.Page_DEFAULT;
import interfaces.Crawler;

public class Crawler_DEFAULT implements Crawler {
	public Page getPage(String url) {
		
		Page ret = null;
		URL pageURL = null;
		try {
			pageURL = new URL(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			return null;
		}
		InputStream stream = null;
		try {
			 stream = pageURL.openStream();
		} catch (IOException e) {
			return null;
		}
		BufferedReader reader = null;
		String source="";
		try {
			reader = new BufferedReader(new InputStreamReader(stream,"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			return null;
		}
		String line = null;
		while(true)
		{
			try {
				line = reader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				continue;
			}
			if(line==null)break;
			source=source+line;
		}
		ret = new Page_DEFAULT(url,source);
		return ret;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println(getPage("http://acm.hdu.edu.cn/listproblem.php?vol=53"));
	}


}
