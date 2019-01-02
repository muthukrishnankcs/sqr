package files.businessFunctions;
import org.apache.log4j.Logger;
public class LoggerMethods {
    static Logger logger = Logger.getLogger(LoggerMethods.class.getName());
    public static void main(String[] args) {
        System.setProperty("log_dir", LoggerMethods.class.getSimpleName());
        logger.debug("This is debug message");
        logger.info("This is info message");
        logger.warn("This is warn message");
        logger.fatal("This is fatal message");
        logger.error("This is error message");
        System.out.println("Logic executed successfully....");
    }
}