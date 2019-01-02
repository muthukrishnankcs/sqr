package files.Utilities;

//import org.openqa.jetty.util.Credential;

public class TestUtil {

	
	//find if the test suite is runnable
	
	public static boolean isSuiteRunnable(Xls_Reader xls , String suiteName){
		
		boolean isExecutable = false;
		
		for(int i = 2; i<= xls.getRowCount("Test Suite") ; i++){

			String suite = xls.getCellData("Test Suite", "TSID", i);
			String runmode = xls.getCellData("Test Suite", "Runmode", i);
			
			if(suite.equalsIgnoreCase(suiteName)){
				
				if(runmode.equalsIgnoreCase("Y")){
					
					isExecutable = true;
					
				} else{
					
					isExecutable = false;
				}
			}
		}
		
		xls = null; // Release Memory
		return isExecutable;
		
		
	}
	
	
	
	//returns true if runmode of the test is equal to Y
	public static boolean isTestCaseRunnable(Xls_Reader xls , String testCaseName) {
	
		boolean isExecutable = false;
		
		for(int i = 2 ; i<=xls.getRowCount("Test Cases") ; i++) {
			
			String tcid = xls.getCellData("Test Cases", "TCID", i);
			String runmode = xls.getCellData("Test Cases", "Runmode", i);
			if (tcid.equalsIgnoreCase(testCaseName)){
				
				if (runmode.equalsIgnoreCase("Y")){
					System.out.println("run mode is yes");
					isExecutable = true;
				} else {
					
					System.out.println("run mode is no");
					isExecutable = false;
					
				}
			}
			
		}
		
		xls = null; // Release Memory
		return isExecutable;
		
}

	
	
	//return the test data from a test in a 2 dim array
	public static Object[][] getData(Xls_Reader xls , String testCaseName) {
		
		if(! xls.isSheetExist(testCaseName)){
			
			xls = null;
			return new Object[1][0];
		}
		
		
		int rows = xls.getRowCount(testCaseName);
		int cols = xls.getColumnCount(testCaseName);
//		System.out.println(rows);
//		System.out.println(cols);

		Object[][] data = new Object[rows-1][cols-3];
		for(int rowNum = 2 ; rowNum<=rows ; rowNum++){
			
			for(int colNum = 0 ; colNum<=cols-4 ; colNum++){
				
//				System.out.println(xls.getCellData(testCaseName, colNum, rowNum));
				data[rowNum-2][colNum] = xls.getCellData(testCaseName, colNum, rowNum);
			}
		}
		xls = null;  // Release Memory
		return data;
	}
	
	
	
	//update results for a particular data set
	public static void reportDataSetResult(Xls_Reader xls , String testCaseName , int rowNum , String result){
		
		xls.setCellData(testCaseName, "Results", rowNum, result);
		
	}
	
	
	// checks Runmode for dataSet
	public static String[] getDataSetRunmodes(Xls_Reader xlsFile,String sheetName){
		String[] runmodes=null;
		if(!xlsFile.isSheetExist(sheetName)){
			xlsFile=null;
			sheetName=null;
			runmodes = new String[1];
			runmodes[0]="Y";
			xlsFile=null;
			sheetName=null;
			return runmodes;
		}
		runmodes = new String[xlsFile.getRowCount(sheetName)-1];
		for(int i=2;i<=runmodes.length+1;i++){
			runmodes[i-2]=xlsFile.getCellData(sheetName, "Runmode", i);
		}
		xlsFile=null;
		sheetName=null;
		return runmodes;
		
	}
	

	// return the row num for a test
	public static int getRowNum(Xls_Reader xls, String id){
		for(int i=2; i<= xls.getRowCount("Test Cases") ; i++){
			String tcid=xls.getCellData("Test Cases", "TCID", i);
			
			if(tcid.equals(id)){
				xls=null;
				return i;
			}
			
			
		}
		
		return -1;
	}
	
	
	// return the row num for a test
	public static String[] getRowNum_PropertiesFile(Xls_Reader xls, String id){

		String[] credential = new String[3];
		
		for(int i=2; i<= xls.getRowCount("Login Details") ; i++){
			String tcid=xls.getCellData("Login Details", "APP_Credentials", i);
			
			if(tcid.equals(id)){
				String user_Name = xls.getCellData("Login Details", "Username", i);
				String password =  xls.getCellData("Login Details", "Password", i);
				
				credential[0] = user_Name;
				credential[1] = password;

				xls=null;
				break;
			}
			
			
		}
		return credential;
		
	}
	
}
	
