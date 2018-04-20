package control;

import bean.Page;
import interfaces.Analyzer;

public class Analyzer_30 implements Runnable{

	
	public static int ANALYZER_EMPTY_DELAY=10000;
	public static int ANALYZER_NORMAL_DELAY=100;

	public static String DEFAULT_ANALYZER_NAME="analyzer.Analyzer_DEFAULT";
	
	
	public static void config()
	{
		String className = null;
		Integer number = null;
		
		number = Config.getInteger("ANALYZER_EMPTY_DELAY");
		if(number != null)ANALYZER_EMPTY_DELAY=number;
		number = Config.getInteger("ANALYZER_NORMAL_DELAY");
		if(number != null)ANALYZER_NORMAL_DELAY=number;

		className = Config.getString("DEFAULT_ANALYZER_NAME");
		DEFAULT_ANALYZER_NAME = className;
	}
	
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
		config();
		Page now = null;
		Analyzer analyzer = Analyzer_30.getAnalyzer(DEFAULT_ANALYZER_NAME);
		while(true)
		{
			
			now = Main.communication.getSource();
			if(now == null) 
			{
				try {
					Thread.sleep(ANALYZER_EMPTY_DELAY);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else
			{
				Page tar = analyzer.analyze(now);
				Main.communication.putResult(tar);
				
				try {
					Thread.sleep(ANALYZER_NORMAL_DELAY);
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
					Thread.sleep(ANALYZER_EMPTY_DELAY);
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
				Thread.sleep(ANALYZER_NORMAL_DELAY);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
