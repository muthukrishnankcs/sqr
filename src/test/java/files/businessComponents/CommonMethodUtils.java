package files.businessComponents;

import java.util.Random;

public class CommonMethodUtils {

    public CommonMethodUtils()
    {

    }
    public static int generateRandomNumber()
    {
        Random randomGenerator = new Random();
        int value = randomGenerator.nextInt(10000);
        return value;

    }
}
