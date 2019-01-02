package files.reusableLibrary;


import com.fasterxml.jackson.databind.ObjectMapper;
import files.POJOClasses.Request.GitApiPojo;
import files.POJOClasses.Response.Repo;
import files.Utilities.Reporting;
import sun.security.pkcs.ParsingException;

import java.io.File;
import java.io.IOException;

//TestBase
public class GenericKeywords extends TestBase{

	public static String outputDirectory;
	public static int currentStep , screenshotNo , failureNo ;
	public static boolean testFailure;

	public static GitApiPojo readJsonFile(String jsonFileName) throws IOException
	{
	    try {

            APP_LOGS.debug("Read Json File : "+jsonFileName);
            ObjectMapper objectMapper = new ObjectMapper();
            String path = new File(".").getCanonicalPath().concat("/src/com/sprinklr/resources/"+jsonFileName);
            GitApiPojo input = objectMapper.readValue(new File(path), GitApiPojo.class);
            APP_LOGS.debug("Read JSON File Successfully : "+jsonFileName);
            return input;
        }
        catch(ParsingException e)
        {
            APP_LOGS.debug("Error : Error in reading JSON : "+e);
            Reporting.fail("ERROR :Reading JSON :"+e);
        }
        return null;

	}

	public static Repo convertStringToPojo(String jsonBody) throws IOException
	{
        try {
            APP_LOGS.debug("Convert String to Pojo : ");
            ObjectMapper objectMapper = new ObjectMapper();
            Repo input = objectMapper.readValue(jsonBody, Repo.class);
            APP_LOGS.debug("String to Pojo is Converted Successfully: ");
            return input;
        }
        catch(ParsingException e)
        {
            APP_LOGS.debug("Error : Error in converting String to Pojo : "+e);
            Reporting.fail("ERROR :Convert String to Pojo :"+e);
        }
        return null;
    }

}
