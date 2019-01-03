package files.testCases;

import files.POJOClasses.OutcomeRepositoryResponse;
import files.businessFunctions.AuthenticationMethods;
import files.POJOClasses.Response.Repo;
import files.utilities.ErrorUtil;
import files.utilities.TestUtil;
import files.reusableLibrary.GenericKeywords;
import files.reusableLibrary.TestSuiteBase;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class TC01 extends TestSuiteBase {

	
	String runmodes[] = null;
	static int count = -1;
	
	static boolean pass = false;
	static boolean fail = false;
	static boolean skip = false;
	static boolean isTestPass = true;
	
	
	@BeforeTest
	public void checkTestSkip() {
		
		APP_LOGS.debug("Checking Runmode of '"+this.getClass().getSimpleName()+"' :");
		if(!TestUtil.isTestCaseRunnable(Sprint1xls, this.getClass().getSimpleName())) {
			APP_LOGS.debug("SKIPPED : RunMode of '"+this.getClass().getSimpleName()+"' is set to 'No' So Skipped :");
			throw new SkipException("SKIPPED : RunMode of '"+this.getClass().getSimpleName()+"' is set to 'No' So Skipped :");
		}
		
		//Load the runmodes of the tests
		runmodes = TestUtil.getDataSetRunmodes(Sprint1xls, this.getClass().getSimpleName());

	}
	
	
	@Test(groups = {"Regression"} , dataProvider="getTestData" , description = "Validate Create Repo")
	public void validateApi(String jsonBody, String requestFile){
		
		// test the runmode of the current dataset
		count++;
		APP_LOGS.debug("The count is :"+count);
		APP_LOGS.debug("The "+runmodes[count]+" is :"+runmodes[count]);
		if(! runmodes[count].equalsIgnoreCase("Y")){
			skip = true;
			APP_LOGS.debug("SKIPPED : Run mode for testcase '"+this.getClass().getSimpleName()+"' setdata set to 'NO' for index :"+count);
			throw new SkipException("Run mode for testcase '"+this.getClass().getSimpleName()+"' setdata set to 'NO' for index :"+count);
		}
				
		
		try{
			AuthenticationMethods.setBaseUri();
			String response = given()
					.contentType(ContentType.JSON).body(jsonBody)
					.header("content-type", "application/json")
					.when()
					.post("/user/repos").then().statusCode(201)
					.and().body("name",equalTo(GenericKeywords.convertStringToPojo(jsonBody).getName()))
					.extract().response().getBody().print();

			Repo r = GenericKeywords.convertStringToPojo(response);
			given()
					.get("/repos/"+r.getOwner().getLogin()+"/"+r.getName()).then().statusCode(200);

//			Using File Name
			/*given()
					.contentType(ContentType.JSON).body(GenericKeywords.readJsonFile(requestFile))
					.post("users").then().log().all().statusCode(201)
					.extract().response().print();*/

		}catch(Throwable t){
		
			ErrorUtil.addVerificationFailure(t);
			fail = true;
			Assert.fail("The error is :"+t);
			//return; //If dont want to continue include return 
		}

	}
	

	@AfterMethod
	public void reportDataSetResult() {
		if(skip){
			TestUtil.reportDataSetResult(Sprint1xls, this.getClass().getSimpleName(), count+2, "SKIP");
		} else if(fail){
			TestUtil.reportDataSetResult(Sprint1xls, this.getClass().getSimpleName(), count+2, "FAIL");
			isTestPass = false;
		} else {
			TestUtil.reportDataSetResult(Sprint1xls, this.getClass().getSimpleName(), count+2, "PASS");
		}
		
		skip = false;
		fail = false;
	}
	
	
	
	@AfterTest
	public void reportTestResult() {
		if(isTestPass){
			
			TestUtil.reportDataSetResult(Sprint1xls, "Test Cases", TestUtil.getRowNum(Sprint1xls, this.getClass().getSimpleName()), "PASS");
			
		}
			else {
				
				TestUtil.reportDataSetResult(Sprint1xls, "Test Cases", TestUtil.getRowNum(Sprint1xls, this.getClass().getSimpleName()), "FAIL");
			}

		}

	@DataProvider
	public Object[][] getTestData() {
		
		return TestUtil.getData(Sprint1xls, this.getClass().getSimpleName());
		
	}

}
