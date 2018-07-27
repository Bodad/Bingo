package common;

import org.apache.logging.log4j.Logger;

public class LogManager {
    private final Logger logger = org.apache.logging.log4j.LogManager.getLogger("Bingo");

    public void log(String message){
        logger.info(message);
    }

    public void log(String message, Object... object){
        logger.info(message, object);
    }

    public void logError(String message, Object... objects){
        logger.error(message, objects);
    }
}
