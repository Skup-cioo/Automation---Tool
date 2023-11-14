package stepsDefiniton;

import common.Common;
import io.cucumber.java.*;;

public class Hooks extends Common {
    @After
    public void closeDriver() {
        if (driverExist) {
            Common.driver.close();
            System.out.println("Closed Driver");
        }
    }
}
