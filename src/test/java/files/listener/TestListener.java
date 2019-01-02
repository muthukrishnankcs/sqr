package files.listener;

import files.Utilities.Reporting;
import files.reusableLibrary.GenericKeywords;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

public class TestListener extends TestListenerAdapter {
  	
	
   @Override
   public void onTestFailure(ITestResult result) {
		String srcPath= GenericKeywords.outputDirectory+"/Screenshots";
		Reporter.setCurrentTestResult(result);
		if (GenericKeywords.testFailure)
		{
			GenericKeywords.embedScreenshot("red",srcPath+"/TestFailure"+GenericKeywords.failureNo);
			GenericKeywords.testFailure=false;
		}
		else
		{
			Reporting.screenshot(result.getName());
			GenericKeywords.embedScreenshot("red",srcPath+"/"+result.getName());
		}
		Reporter.log("<font color='red'>");
		Reporter.setCurrentTestResult(null);
		Reporter.log("</font>");
	   }
     
	   @Override
	   public void onTestSkipped(ITestResult result) {
	  // will be called after test will be skipped
	   }
	  
	   @Override
	   public void onTestSuccess(ITestResult result) {
	  // will be called after test will pass 
	   }
}