package com.files.businessFunctions;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


public class PropertyClass {
    InputStream inputStream;
    public static Properties prop = new Properties();


    public PropertyClass() {
        try {
            String propFileName = "config.properties";
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }


        } catch (
                IOException ex) {

            ex.printStackTrace();
        }

    }


}

