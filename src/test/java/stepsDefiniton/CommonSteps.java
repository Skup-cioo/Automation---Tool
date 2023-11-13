package stepsDefiniton;

import common.Common;
import io.cucumber.java.en.Given;

public class CommonSteps {
    private static final String APP_AND_PAGE_NAME = "[COMMON] ";
    @Given(APP_AND_PAGE_NAME + "Open app '{}'")
    public static void openApp(String nameOfApp) {
        Common.openApp(nameOfApp);
    }

    @Given(APP_AND_PAGE_NAME + "Create a web driver")
    public static void clickElementContainsText() {
        Common.createDriver();
    }

    @Given(APP_AND_PAGE_NAME + "maximize window")
    public static void maximizeWindow(){
        Common.maxWindow();
    }

    @Given(APP_AND_PAGE_NAME + "Take a screenShot")
    public static void takeScreenshot(){
        Common.takeScreenShotForDriver();
    }
}
