package files.businessFunctions;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;

public class BaseClass extends PropertyClass  {

    public BaseClass()
    {
        RestAssured.baseURI = prop.getProperty("BASE_URL");
        System.out.println(RestAssured.baseURI);
        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName(prop.getProperty("username"));
        authScheme.setPassword(prop.getProperty("password"));
        RestAssured.authentication = authScheme;


    }



}
