package util.algorithm;


/**
 * @author Tank 2017-10-16
 * @see 接受一个模板类进行初始化，之后可以对任意字符串的指定位置进行匹配，匹配成功则返回成功时的位置，匹配失败时返回-1
 * @see 标准算法，时间性能非常好的匹配算法
 * 
 */
public class KMP {

	int f[];int endd;
	String model;
	public KMP(String str)
	{
		this.init(str);
	}
	public KMP(){}
	public void init(String model)
	{
		int len=model.length();
		this.model=model;
		this.endd=len;
		this.f=new int[len+10];
		f[0]=f[1]=0;
		for(int i=1;i<len;++i)
		{
			int j=f[i];
			while(j!=0&&model.charAt(i)!=model.charAt(j))j=f[j];
			f[i+1]= model.charAt(j)==model.charAt(i)? j+1:0;
		}
	}
	/**
	 * @see 返回第一位命中时的位置。
	 * @param str
	 * @param pos
	 * @return
	 */
	public int match(String str,int pos)
	{
		int ret=-1;int len=str.length();
		int j=f[0];
		for(int i=pos;i<len;++i)
		{
			while(j!=0&&str.charAt(i)!=model.charAt(j))j=f[j];
			j= str.charAt(i)==model.charAt(j)? j+1:0;
			if(j==endd)return i-model.length()+1;
		}
		return ret;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}