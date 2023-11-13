package stepsDefiniton;

import pageObjects.MousePageObject;
import common.Timeouts;
import io.cucumber.java.en.*;

public class MouseStepDefinition {
    private static final String APP_AND_PAGE_NAME = "[MOUSE][Main Page] ";
    private static final String APP_AND_PAGE_NAME_OTHERS = "[MOUSE][Others] ";

    @And(APP_AND_PAGE_NAME + "Click element with text '{}'")
    public void clickElementContainsText(String text) {
        MousePageObject.clickElementAndTakeScreenShot(text);
    }

    @Then(APP_AND_PAGE_NAME + "Page is displayed")
    public void pageIsDisplayed() {
        MousePageObject.pageIsDisplayed();
    }

    @And(APP_AND_PAGE_NAME_OTHERS + "Drag and drop element")
    public void dragAndDropElements() {
        MousePageObject.dragAndDropElements();
        MousePageObject.waitSomeTime(Timeouts.MIN);
    }

    @And(APP_AND_PAGE_NAME_OTHERS + "Drag and drop element to position: X = '{}' Y = '{}'")
    public void dragElementToPostition(int x, int y) {
        MousePageObject.dragElementToPostition(x, y);
    }

    @Then(APP_AND_PAGE_NAME + "Click ok popUp")
    public void clickOk() {
        MousePageObject.clickOkElementIfDisplayer();
    }

    @Then(APP_AND_PAGE_NAME_OTHERS + "Replace the letter '{}' with the letter '{}'")
    public void replaceLetters(char firstLetter, char secondLetter) {
        MousePageObject.replaceLetters(firstLetter, secondLetter);
    }
}
