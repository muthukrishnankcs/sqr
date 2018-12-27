//package com.github.utils;
//
//import io.restassured.RestAssured;
//import io.restassured.authentication.PreemptiveBasicAuthScheme;
////import org.apache.log4j.BasicConfigurator;
////import org.apache.log4j.PropertyConfigurator;
////import org.apache.logging.log4j.Logger;
//import org.testng.annotations.BeforeClass;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Properties;
//
//
//public class ServerUtils {
//
//    InputStream inputStream;
//    @BeforeClass
//    public void setBaseUri () throws IOException {
//
//        Properties prop = new Properties();
//        InputStream input = null;
//
//        try {
////            Properties prop = new Properties();
//            String propFileName = "config.properties";
//            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
//
//            if (inputStream != null) {
//                prop.load(inputStream);
//            } else {
//                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
//            }
//
//
//        } catch (IOException ex) {
//
//            ex.printStackTrace();
//            // get the property value and print it out
//        }
////        RestAssured.baseURI = "https://api.github.com";
//        RestAssured.baseURI = prop.getProperty("BASE_URL");
//        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
//        authScheme.setUserName(prop.getProperty("username"));
//        authScheme.setPassword(prop.getProperty("password"));
//        RestAssured.authentication = authScheme;
//
//    }
//
//}
