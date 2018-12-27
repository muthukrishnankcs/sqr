package files.businessComponents;
import org.apache.log4j.Logger;
public class LoggerUtils {
    static Logger logger = Logger.getLogger(LoggerUtils.class.getName());
    public static void main(String[] args) {
        System.setProperty("log_dir", LoggerUtils.class.getSimpleName());
        logger.debug("This is debug message");
        logger.info("This is info message");
        logger.warn("This is warn message");
        logger.fatal("This is fatal message");
        logger.error("This is error message");
        System.out.println("Logic executed successfully....");
    }
}