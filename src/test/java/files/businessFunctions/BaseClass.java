package files.businessFunctions;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;

public class BaseClass{

    public static void setAuthentication()
    {

        RestAssured.baseURI = ConfigProperty.readConfig().getProperty("BASE_URL");
        System.out.println(RestAssured.baseURI);
        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName(ConfigProperty.readConfig().getProperty("username"));
        authScheme.setPassword(ConfigProperty.readConfig().getProperty("password"));
        RestAssured.authentication = authScheme;

    }



}
