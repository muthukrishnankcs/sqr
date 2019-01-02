package files.reusableLibrary;


import files.Utilities.Common;
import files.Utilities.Output;
import files.Utilities.Xls_Reader;
import org.apache.log4j.Logger;
import org.testng.TestNG;

import java.io.FileInputStream;
import java.util.Properties;

public class TestBase extends Common {

		public static Logger APP_LOGS = null;
		public static Properties CONFIG = null;
		public static Xls_Reader Sprint1xls = null;
		public static Xls_Reader suiteXls = null;
		public static boolean isInitialized = false;

		public static TestNG testNG;

	//Initializing the tests
	public void Initialize() throws Exception { 
	
		//logs
		if (!isInitialized) {

			APP_LOGS = Logger.getLogger("devpinoyLogger");
		
			//config Properties
			APP_LOGS.debug("Proceed to start Loading config.Properties Files :");
			CONFIG = new Properties();
			FileInputStream ip = new FileInputStream("./src/test/java/files/logs/config.properties");
			CONFIG.load(ip);
			APP_LOGS.debug("config.properties Files Loaded Sucessfully :");

			//xls file
			APP_LOGS.debug("Proceed to start Loading XLS Files :");
			Sprint1xls = new Xls_Reader("./src/test/java/files/xls/Sprint_1.xlsx");
			suiteXls  = new Xls_Reader("./src/test/java/files/xls/Suite.xlsx");

			//Create OutputDirectory
			APP_LOGS.debug("Proceed to Create the OutputDirectory :");
			Output.createOutputDirectory();
			
			//Delete All Files Under 'DownloadFile' Folder
			APP_LOGS.debug("Proceed to Delete All Files Under 'DownloadFile' Folder :");
			CommonFunctions.delete_Files(CommonFunctions.GetCanonicalPath()+"/DownloadFile");
			
			
			APP_LOGS.debug("Proceed to Set the OutputDirectory :");
			//Set OutputDirectory
			testNG = new TestNG();
			testNG.setOutputDirectory(GenericKeywords.outputDirectory);	
			isInitialized = true;
		}	

    }
} 
