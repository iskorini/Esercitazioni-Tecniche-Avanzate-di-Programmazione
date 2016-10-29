package app;

import org.apache.log4j.Logger;

/**
 * Created by federicoschipani on 29/10/2016.
 */
public class MainClass {

    final static Logger LOGGER = Logger.getLogger(MainClass.class);

    public String myMethod() {
        LOGGER.info("myMethod called");
        return "Hello World!";
    }

    public static void main(String[] args) {
        System.out.println(new MainClass().myMethod());
    }


}
