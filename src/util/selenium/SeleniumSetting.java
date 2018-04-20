package util.selenium;

import org.openqa.selenium.firefox.FirefoxProfile;

public class SeleniumSetting {

	public static String downLoadPath;
	public static final String firfoxPath="D:\\Firefox\\firefox.exe";
	
	
	
	public static FirefoxProfile FilefoxDriverProfile () throws Exception  
	 {  
	      //声明一个profile对象  
	      FirefoxProfile profile = new FirefoxProfile();  
	      profile.setPreference("browser.download.folderList",2);  
	      profile.setPreference("browser.download.manager.showWhenStarting", false);  
	      profile.setPreference("browser.download.dir",downLoadPath);  
	      profile.setPreference("browser.helperApps.neverAsk.openFile",  
	              "application/octet-stream,application/xhtml+xml,application/xml,application/x-msdownload,application/octet/octet-stream,application/exe,txt/csv,application/pdf,application/x-msexcl,application/x-excel,application/excel,image/png,image/jpeg,text/html,text/plain,text/x-c");  
	      profile.setPreference("browser.helperApps.neverAsk.saveToDisk",  
	              "application/xhtml+xml,application/xml,application/x-msdownload,application/octet/octet-stream,application/exe,txt/csv,application/pdf,application/x-msexcl,application/x-excel,application/excel,image/png,image/jpeg,text/html,text/plain,text/x-c");  
	      //不会打开未知MIMe类型  
	      profile.setPreference("browser.helperApps.alwaysAsk.force", false);  
	      //不会弹出警告框  
	      profile.setPreference("browser.download.manager.alertOnEXEopen", false);  
	      profile.setPreference("browser.download.manager.focusWhenStarting", false);  
	      profile.setPreference("browser.download.manager.useWindow", false);  
	      profile.setPreference("browser.download.manager.showAlertOnComplete", false);  
	      profile.setPreference("browser.download.manager.closewhenDone", false);  
	      return profile;  
	 }  
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
