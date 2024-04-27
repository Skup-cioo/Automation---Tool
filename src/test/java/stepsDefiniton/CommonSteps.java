package stepsDefiniton;

import common.Common;
import common.Timeouts;
import common.ownResources.GeneratorPolishName;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pageObjects.AutomationPageObject;
import pageObjects.KeyboardPageObject;
import pageObjects.o2PageObject;

import java.util.Random;

public class CommonSteps {
    private static final String APP_AND_PAGE_NAME = "[COMMON] ";

    @Given(APP_AND_PAGE_NAME + "Open app '{}'")
    public static void openApp(String nameOfApp) {
        Common.openApp(nameOfApp);
    }

    @Given(APP_AND_PAGE_NAME + "Create a web driver")
    public static void clickElementContainsText() {
        Common.createDriver();
        Common.waitSomeTime(Timeouts.MEDIUM);
    }

    @Given(APP_AND_PAGE_NAME + "maximize window")
    public static void maximizeWindow() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Common.maxWindow();
    }

    @Given(APP_AND_PAGE_NAME + "Take a screenShot")
    public static void takeScreenshot() {
        Common.takeScreenShotForDriver();
    }


    private static final String APP_AND_PAGE_NAME1 = "[o2] ";
    String name = GeneratorPolishName.getRandomName();
    String naz = GeneratorPolishName.getRandomLastName();

    @Then(APP_AND_PAGE_NAME1 + "user put name '{}' and last name '{}'")
    public void putTextToInput(String firstName, String lastName) {
        if (firstName.equals("RANDOM_NAME")) {
            firstName = name;
        }
        if (lastName.equals("RANDOM_LAST_NAME")) {
            lastName = naz;
        }
        o2PageObject.fillFirstNameAndLastName(firstName, lastName);
    }


    @Then(APP_AND_PAGE_NAME1 + "user put login")
    public void putEmailLogin() {
        Random rand = new Random();
        String login = name + naz + rand.nextInt(30) + 10;
        o2PageObject.putlogin(login);
    }

    @Given(APP_AND_PAGE_NAME1 + "Select '{}' option from only one option field")
    public void selectOneOption(int option) {
        o2PageObject.selectOneOption(option);
    }

    @Given(APP_AND_PAGE_NAME1 + "wypelniamy date")
    public void putData() throws InterruptedException {
        o2PageObject.putData();
        Thread.sleep(10000);
    }

    @Given(APP_AND_PAGE_NAME1 + "Kliknij dalej")
    public void clickDalejButton() {
        o2PageObject.clickDalejButton();
    }


    @Then(APP_AND_PAGE_NAME1 + "Page is displayed")
    public void pageIsDisplayed() {
        o2PageObject.pageIsDisplayed();
    }

    @Then(APP_AND_PAGE_NAME1 + "uzytkownik Wpisuje hasla")
    public void putPasswords() {
        o2PageObject.wpiszHaslo();
    }
}
