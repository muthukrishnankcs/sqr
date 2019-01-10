package com.files.businessFunctions;

import java.util.Random;

public class CommonMethods {

    public CommonMethods()
    {

    }
    public static int generateRandomNumber()
    {
        Random randomGenerator = new Random();
        int value = randomGenerator.nextInt(10000);
        return value;

    }
}
