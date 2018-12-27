package com.github.api.TestCases;

//import io.restassured.RestAssured;
import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class BasicAuthenticateGetUserDetails {

    @BeforeClass
    public void setBaseUri () {

        RestAssured.baseURI = "https://api.github.com";
        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName("PramatiImaginea");
        authScheme.setPassword("Sprinklr123");
        RestAssured.authentication = authScheme;
    }

    @Test
    public void getGitExample()  {
        Response response  = given()
                .header("content-type", "application/json")
                .when()
                .get("/user").then().extract().response();
        System.out.println(response.asString());


    }

}
