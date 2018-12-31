package files.reusableLibrary;

import files.Utilities.Reporting;

import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CommonFunctions extends GenericKeywords{

	public  static String generateRandomnoUsingCalender()
	{
	
		Calendar calendar = new GregorianCalendar();
		String min,hr,sec,mon,day;
		
		hr=("0"+calendar.get(Calendar.HOUR));
		hr=hr.substring(hr.length()-2);
	
		min=("0"+calendar.get(Calendar.MINUTE));
		min=min.substring(min.length()-2);
	
		sec=("0"+calendar.get(Calendar.SECOND));
		sec=sec.substring(sec.length()-2);
	
		mon=("0"+(calendar.get(Calendar.MONTH)+1));
		mon=mon.substring(mon.length()-2);
	
		day=("0"+calendar.get(Calendar.DAY_OF_MONTH));
		day=day.substring(day.length()-2);
	
		String random_No = mon+day+min+sec;
		
		return random_No;
	}

	
	 public static void verifyTwoStringMessage(String AppMsg , String ExpectedMsg) throws Exception {
		
		Reporting.log("Proceed to Verify the Application error(or)success message with expected error(or)success message :");
		if(AppMsg.contains(ExpectedMsg)){
			Reporting.log("The Application error(or)success message is matches with expected error(or)success message :");
		}else{
			Reporting.fail("Expected error(or)success Message :"+ExpectedMsg);
			Reporting.fail("Actual error(or)success Message :"+AppMsg);
			Reporting.fail("The Application err msg does not matches with expected err msg :");
			
		}
	}
	 
	 
		public static void Verify_DefaultSelectedValues_InDropdown(String AppValue , String ExpectedValue) throws Exception {
			
			Reporting.log("Proceed to verify two dropdown values :");
			if(AppValue.contains(ExpectedValue)){
				Reporting.log("The Application default selected values matches with expected values :");
			}else{
				Reporting.warning("Expected Default selected value :"+ExpectedValue);
				Reporting.warning("Actual selected values :"+AppValue);
				Reporting.fail("The Application default selected values is not matches with expected value :");
				
			}
		}
		
		
	 public static String  GetCanonicalPath() throws Exception{
			File curdir = new File(".");
			String canonical_Path = curdir.getCanonicalPath();
			return canonical_Path;
	 }
	 
	 
	 public static void delete_Files(String file_path) throws Exception {
		 
		 File fin = new File(file_path);
		    File[] finlist = fin.listFiles();       
		    for (int n = 0; n < finlist.length; n++) {
		        if (finlist[n].isFile()) {
		        System.gc();
		        Thread.sleep(2000);
		            finlist[n].delete();
		        }
		    }
		    
	 }

}
