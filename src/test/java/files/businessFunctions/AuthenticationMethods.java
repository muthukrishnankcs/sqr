package files.businessFunctions;

import files.reusableLibrary.GenericKeywords;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import org.testng.Reporter;

public class AuthenticationMethods extends GenericKeywords {

	public static void setBaseUri() {
		Reporter.log("Proceed to API Authentication : ");
		RestAssured.baseURI = CONFIG.getProperty("uri");
		PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
		authScheme.setUserName(CONFIG.getProperty("username"));
		authScheme.setPassword(CONFIG.getProperty("password"));
		RestAssured.authentication = authScheme;
	}
}
