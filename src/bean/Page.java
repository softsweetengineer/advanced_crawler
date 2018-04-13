package bean;

public abstract class Page {

	public String url;
	public String source;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public Page(){}
	public Page(Page p)
	{
		this.url=new String(p.url);
		this.source=new String(p.source);
	}
	public Page(String url,String source)
	{
		this.url=url;
		this.source=source;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
