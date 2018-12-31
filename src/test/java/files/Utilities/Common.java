package files.Utilities;

import files.reusableLibrary.TestBase;
import org.testng.Reporter;


public class Common{


	public static void supportSeleniumRC()
	{

		TestBase.APP_LOGS.debug("Creating support for selenium RC commands");
		//GenericKeywords.selenium = new WebDriverBackedSelenium(GenericKeywords.driver, "http://www.yoursite.com");
		TestBase.APP_LOGS.debug("Selenium RC supported now");
	//	GenericKeywords.elementWaitTime=Integer.parseInt(getConfigProperty("WebElementWaitTime"));
	}
	
	public enum identifierType
	{
		xpath,
		name,
		id,
		linktext,
		partiallinktext,
		classname,
		cssSelector,
		tagname
	}
	
	public enum browserType
	{
		FIREFOX,
		IEXPLORER,
		CHROME,
		OPERA
	}
	
	
	public static void embedScreenshot(String colour, String pathAndFile)
	{
		
		//scrPath+"\\"+result.getName()
		
		Reporter.log("<font color='"+colour+"'> Screenshot repository :- "+pathAndFile+".jpg");
		Reporter.log("<a href='file:///"+pathAndFile+".jpg'> ");
		Reporter.log("<img src='file:///"+pathAndFile+".jpg' height='200' width='200'/></a></font>");

	}
	
	
}
