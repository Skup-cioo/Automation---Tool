package pageObjects;

import common.Common;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MousePageObject extends Common {
    private static final By seleniumElement = By.xpath("(//a[contains(text(),'Selenium')])[1]");
    private static final By okButton = By.xpath("(//a[contains(text(),'OK')])[1]");
    private static final By draggableElement = By.xpath("(//div[@id='draggable'])[1]");
    private static final By droppableElement = By.xpath("(//div[@id='droppable'])[1]");

    private static WebElement letterElement(char letter) {
        return driver.findElement(By.xpath(String.format("//li[contains(@name, '%s')]", letter)));
    }

    public static void clickElementAndTakeScreenShot(String text) {
        By element = Common.elementContainsText(text);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(element))).click();
        Common.takeScreenShotForDriver();
    }

    public static void pageIsDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(seleniumElement)));
    }

    public static void dragAndDropElements() {
        Common.dragAndDropElement(draggableElement, droppableElement);
    }

    public static void dragElementToPostition(int x, int y) {
        Common.dragAndDropBy(draggableElement, x, y);
    }

    public static void clickOkElementIfDisplayer() {
        if (driver.findElement(okButton).isDisplayed()) {
            driver.findElement(okButton).click();
        }
    }

    public static void replaceLetters(char firstLetter, char secondLetter) {
        WebElement firstLetterElement = letterElement(firstLetter);
        WebElement secondLetterElement = letterElement(secondLetter);
        Common.clickAndHold(firstLetterElement, secondLetterElement);
    }
}
