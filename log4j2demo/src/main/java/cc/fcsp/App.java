package cc.fcsp;

import cc.fcsp.test.AnotherApp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Hello world!
 */
public class App {

    private static final Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) {
        logger.debug("hello {}","world");
//        System.out.println("Hello World!");
        AnotherApp.test();
    }
}
