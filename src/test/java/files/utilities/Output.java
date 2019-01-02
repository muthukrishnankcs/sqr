package files.utilities;

import files.reusableLibrary.GenericKeywords;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class Output
{
	public static void createOutputDirectory()
	{
	//outputDirectory="";
	File curdir = new File(".");
  
  	Calendar calendar = new GregorianCalendar();
  	String am_pm,min,hr,sec,yr,mon,day;
  	
  	hr=("0"+calendar.get(Calendar.HOUR));
  	hr=hr.substring(hr.length()-2);

  	min=("0"+calendar.get(Calendar.MINUTE));
  	min=min.substring(min.length()-2);
  

  	sec=("0"+calendar.get(Calendar.SECOND));
  	sec=sec.substring(sec.length()-2);

  	yr=""+calendar.get(Calendar.YEAR);
  	//yr=yr.substring(yr.length()-2);

  	mon=("0"+(calendar.get(Calendar.MONTH)+1));
  	mon=mon.substring(mon.length()-2);

  	day=("0"+calendar.get(Calendar.DAY_OF_MONTH));
  	day=day.substring(day.length()-2);

  	if(calendar.get(Calendar.AM_PM) == 0)
	  	am_pm = "AM";
  	else
	  	am_pm = "PM";

  	
  	try 
  	{
  		GenericKeywords.outputDirectory = curdir.getCanonicalPath()+ "/FinalReports/" + yr +"_"+ mon+"_" +day+"_"+hr+"_"+min+"_"+sec+"_"+am_pm;
	    boolean success = (new File(GenericKeywords.outputDirectory)).mkdir();
	    if (success) {
	    	Reporting.log("Directory Create Successflyy");
	      System.out.println("Directory: " + GenericKeywords.outputDirectory + " created");
	    }  else {
	    	Reporting.fail("Failed to create the directory");
	    }
  	} 
  	catch (IOException e) 
  	{
  		// TODO Auto-generated catch block
	  	System.out.println("IO Error while creating Output Directory : "+GenericKeywords.outputDirectory);
  	}

	}
	}
