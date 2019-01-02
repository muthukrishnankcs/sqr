package files.Utilities;

import files.reusableLibrary.GenericKeywords;
import files.reusableLibrary.TestBase;
import org.testng.Assert;
import org.testng.Reporter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Reporting {

	
	
	public static void comment(String Log){
		
		Reporter.log("                                                            ");
		Reporter.log("<font color=SlateBlue><b>*****"+Log+"*****</b></font>" , true);
	}
	
	public static void fail(String Log) {
		
		GenericKeywords.testFailure=true;
		GenericKeywords.currentStep++;
		GenericKeywords.failureNo++;
		Reporter.log("<font color=red>"+GenericKeywords.currentStep+". "+Log+"</font>" , true);
		TestBase.APP_LOGS.debug("FAIL :"+Log);
		takescreenshot(Log);
		Assert.fail("Test Failure : "+Log);
		
	}
	
	public static void log(String Log){
		
		GenericKeywords.currentStep++;
		Reporter.log("<font color=green>"+GenericKeywords.currentStep+". "+Log+"</font>" , true);
		TestBase.APP_LOGS.debug("INFO :"+Log);
	}
	
	
	public static void warning(String Log){
		
		GenericKeywords.currentStep++;
		Reporter.log("<font color=orange>"+GenericKeywords.currentStep+". "+Log+"</font>" , true);
		TestBase.APP_LOGS.debug("WARNING :"+Log);
		
	}
	
	
	public static void takescreenshot(String comment) {
		
		GenericKeywords.screenshotNo++;
		//Reporter.log("<font color='blue'>Manual verification point : <b>"+comment+"</b></font>" , true);
		screenshot("Screenshot"+GenericKeywords.screenshotNo);
		Common.embedScreenshot("blue",GenericKeywords.outputDirectory+"\\Screenshots"+"\\Screenshot"+GenericKeywords.screenshotNo);
	}
	
	
	public static void screenshot(String filename){
		
		String scrPath=GenericKeywords.outputDirectory+"\\Screenshots";
		File file = new File(scrPath);
	    file.mkdir();
	    try {
	        Robot robot = new Robot();
	        Rectangle captureSize = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
	        BufferedImage bufferedImage = robot.createScreenCapture(captureSize);
	        File outputfile = new File(scrPath+"\\"+filename+".jpg");
	        ImageIO.write(bufferedImage, "jpg", outputfile);       
       		//Reporting.log("Taken screenshot of failing screen");
	    }
	    catch(AWTException e) {
	        Reporting.fail("AWT Exception : While taking screenshot of the failing test case");
	    } catch (IOException e) {
	    	Reporting.fail("IO Exception : While taking screenshot of the failing test case");
		}
	}

}

