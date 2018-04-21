package control;

import java.util.Vector;

import bean.Page;
import interfaces.Communication;

public class Communicator_30 implements Communication{

	public Vector<String> urls=new Vector<String>();
	public Vector<Page> sources=new Vector<Page>();
	public Vector<Page> results=new Vector<Page>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
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
	public synchronized void puturl(String url) {
		// TODO Auto-generated method stub
		urls.addElement(url);
	}

	@Override
	public synchronized void putSource(Page p) {
		// TODO Auto-generated method stub
		sources.addElement(p);
	}

	@Override
	public synchronized Page getSource() {
		// TODO Auto-generated method stub
		if(sources.isEmpty())return null;
		Page ret=sources.get(0);
		sources.remove(0);
		return ret;
	}

	@Override
	public synchronized void putResult(Page p) {
		// TODO Auto-generated method stub
		results.addElement(p);
	}

	@Override
	public synchronized Page getResult() {
		// TODO Auto-generated method stub
		if(results.isEmpty())return null;
		Page ret=results.get(0);
		results.remove(0);
		return ret;
	}

	@Override
	public int urlSize() {
		// TODO Auto-generated method stub
		return this.urls.size();
	}

	@Override
	public int sourceSize() {
		// TODO Auto-generated method stub
		return this.sources.size();
	}

	@Override
	public int resultSize() {
		// TODO Auto-generated method stub
		return this.results.size();
	}

}
