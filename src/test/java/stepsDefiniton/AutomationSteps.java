package stepsDefiniton;

import pageObjects.AutomationPageObject;
import common.Common;
import common.GeneratorPolishName;
import common.Timeouts;
import io.cucumber.java.en.*;

public class AutomationSteps {
    private static final String APP_AND_PAGE_NAME = "[AUTOMATION][Main Page] ";

    @Then(APP_AND_PAGE_NAME + "Page is displayed")
    public void pageIsDisplayed() {
        AutomationPageObject.pageIsDisplayed();
    }

    @Then(APP_AND_PAGE_NAME + "user put name '{}' and last name '{}'")
    public void putTextToInput(String firstName, String lastName) {
        if (firstName.equals("RANDOM_NAME")) {
            firstName = GeneratorPolishName.getRandomName();
        }
        if (lastName.equals("RANDOM_LAST_NAME")) {
            lastName = GeneratorPolishName.getRandomLastName();
        }
        AutomationPageObject.fillFirstNameAndLastName(firstName, lastName);
    }

    @And(APP_AND_PAGE_NAME + "choose gender '{}'")
    public void chooseGender(String gender) {
        AutomationPageObject.chooseGender(gender);
    }

    @Given(APP_AND_PAGE_NAME + "Select '{}' option from only one option field")
    public void selectOneOption(int option) {
        AutomationPageObject.selectOneOption(option);
    }

    @Given(APP_AND_PAGE_NAME + "Select first '{}' options from multiple options")
    public void selectMultiplyOptions(int numberOfOptions) {
        AutomationPageObject.selectMultiplyOptions(numberOfOptions);
        Common.waitSomeTime(Timeouts.MIN);
    }

    @Given(APP_AND_PAGE_NAME + "Select first '{}' options begin from '{}' option from multiple options")
    public void selectMultiplyOptions(int numberOfOptionsToCheck, int startOption) {
        AutomationPageObject.selectMultiplyOptions(numberOfOptionsToCheck, startOption);
    }

    @Given(APP_AND_PAGE_NAME + "Select option by name {string} from applicable options")
    public void selectOptionByName(String name) {
        AutomationPageObject.selectOptionByName(name);
    }

    @Given(APP_AND_PAGE_NAME + "Select date '{}'")
    public void automationMainPageSelectDate(String date) {
        AutomationPageObject.selectDate(date);
    }

    @Given(APP_AND_PAGE_NAME + "user move slider to value '{}'")
    public void automationMainPageUserMoveSliderTo(double value) {
        AutomationPageObject.moveSliderTo(value);
    }

    @Then(APP_AND_PAGE_NAME + "user click Submit button")
    public void userClickSubmitButton() {
        AutomationPageObject.clickButton();
    }
}
