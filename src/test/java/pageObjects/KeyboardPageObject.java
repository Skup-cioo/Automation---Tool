package pageObjects;

import common.Common;
import common.NotFoundException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class KeyboardPageObject extends Common {
    private static final By googleImage = By.xpath("//img[@class='lnXdpd']");
    private static final By acceptAllButton = By.xpath("//div[normalize-space()='Zaakceptuj wszystko']");
    private static final By searchInButton = By.xpath("(//input[@name='btnK'])[2]");

    public static void pageIsDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(googleImage)));
    }

    public static void sendText(String text) {
        sendText(text, false);
    }

    public static void sendText(String text, Boolean UpperText) {
        WebElement textInputField = driver.findElement(By.name("q"));
        if (UpperText) {
            actions.keyDown(Keys.SHIFT).sendKeys(textInputField, text).perform();
        } else {
            driver.findElement(By.name("q")).sendKeys(text);
        }
    }

    public static void clickAgreementsIfExist() {
        try {
            Common.clickIfExist(driver.findElement(acceptAllButton));
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void clickSearchButton() {
        try {
            Common.clickIfExist(driver.findElement(searchInButton));
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }
}
