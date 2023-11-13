package stepsDefiniton;

import pageObjects.KeyboardPageObject;
import common.Timeouts;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class KeyboardSteps {
    private static final String APP_AND_PAGE_NAME = "[GOOGLE][Main Page] ";

    @Then(APP_AND_PAGE_NAME + "Page is displayed")
    public void pageIsDisplayed() {
        KeyboardPageObject.pageIsDisplayed();
    }

    @And(APP_AND_PAGE_NAME + "Send text '{}' with Shift")
    public void sendTextAndClickEnter(String text) {
        KeyboardPageObject.sendText(text, true);
        KeyboardPageObject.waitSomeTime(Timeouts.MIN);
    }

    @And(APP_AND_PAGE_NAME + "Accept terms if displayed")
    public void accpetTerms() {
        KeyboardPageObject.clickAgreementsIfExist();
    }

    @Then(APP_AND_PAGE_NAME + "Click Search button")
    public void googleMainPageClickSearchInGoogle() {
        KeyboardPageObject.clickSearchButton();
        KeyboardPageObject.waitSomeTime(Timeouts.MIN);
    }
}
