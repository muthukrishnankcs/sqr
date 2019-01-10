package com.files.businessFunctions;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


public class ConfigProperty {
    static InputStream inputStream;
    public static Properties prop = new Properties();

    public static Properties readConfig() {
        try {
            String propFileName = "config.properties";
            inputStream = ConfigProperty.class.getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
                return prop;
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }


        } catch (
                IOException ex) {

            ex.printStackTrace();
        }

        return null;
    }
}
