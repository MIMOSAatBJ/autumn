package cc.fcsp.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AnotherApp {
    private static final Logger logger = LogManager.getLogger(AnotherApp.class);

    public static void test() {
        logger.debug("another app");
        logger.info("test info lever");
        logger.warn("fuck --");
        logger.error("ou no!");
    }
}
