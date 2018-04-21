package bean;


/**
 * 用于表示页面的对象，为绝对类，强制用户的页面对象继承Page。
 * 默认包含String类型的url和source分别表示网页URL和网页源代码。
 * @author Tank
 * @version 2018年4月21日
 */
public abstract class Page {

	
	private String url;
	private String source;
	
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
