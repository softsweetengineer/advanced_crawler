package control;

import bean.Page;
import interfaces.Analyzer;

public class Analyzer_30 implements Runnable{

	
	public static int EMPTY_DELAY=10000;
	public static int NORMAL_DELAY=100;
	public static String NAME_OF_ANALYZER="analyzer.Analyzer_HOJ";
	public static String DEFAULT_ANALYZER_NAME="analyzer.Analyzer_DEFAULT";
	
	public static Analyzer getAnalyzer(String className)
	{
		Analyzer ret = null;
		try {
			ret = (Analyzer) Class.forName(className).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			return null;
		}
		return ret;
	}

	public void old_run() {
		// TODO Auto-generated method stub
		Page now = null;
		Analyzer analyzer = Analyzer_30.getAnalyzer(NAME_OF_ANALYZER);
		while(true)
		{
			
			now = Main.communication.getSource();
			if(now == null) 
			{
				try {
					Thread.sleep(EMPTY_DELAY);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else
			{
				Page tar = analyzer.analyze(now);
				Main.communication.putResult(tar);
				
				try {
					Thread.sleep(NORMAL_DELAY);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			
		}
				
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Page now = null;
		Analyzer analyzer = null;
		while(true)
		{
			now = Main.communication.getSource();
			if(now == null)
			{
				try {
					Thread.sleep(EMPTY_DELAY);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else
			{
				String url = now.url;
				String className = MapReader.getAnalyzerName(url);
				if(className == null) className = DEFAULT_ANALYZER_NAME;
				analyzer = getAnalyzer(className);
				Page tar = analyzer.analyze(now);
				Main.communication.putResult(tar);
			}
			try {
				Thread.sleep(NORMAL_DELAY);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
