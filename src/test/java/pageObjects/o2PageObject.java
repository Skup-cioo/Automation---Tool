package pageObjects;

import common.Common;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class o2PageObject extends Common {

    private static final By name = By.id("name");
    private static final By nazwisko = By.id("lastName");
    private static final By login = By.id("login");
    private static final By plec = By.id("sex");
    private static final By dzien = By.id("date");
    private static final By month = By.id("month");
    private static final By rok = By.id("year");
    private static final By dalej = By.tagName("button");
    private static final By hasloUstal = By.xpath("//h2[contains(text(),'Ustal silne hasło')][1]");
    private static final By haslo = By.id("password");
    private static final By powtorzHaslo = By.id("passwordRepeat");

    private static WebElement optionInListDropDown;

    public static void fillFirstNameAndLastName(String firstName, String lastName) {
        driver.findElement(name).sendKeys(firstName);
        driver.findElement(nazwisko).sendKeys(lastName);
    }


    public static void putlogin(String login1) {
        driver.findElement(login).sendKeys(login1);
    }

    public static void selectOneOption(int option) {
        optionInListDropDown = driver.findElement(plec);
        select = new Select(optionInListDropDown);
        select.selectByIndex(option);
    }

    public static void putData() {
        driver.findElement(dzien).sendKeys("12");
        driver.findElement(rok).sendKeys("1999");
        optionInListDropDown = driver.findElement(month);
        select = new Select(optionInListDropDown);
        select.selectByIndex(12);
    }
    public static void clickDalejButton() {
        driver.findElement(dalej).click();

    }

    public static void pageIsDisplayed() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void wpiszHaslo() {
        driver.findElement(haslo).sendKeys("SpringBootNajlepszy@1");
        driver.findElement(powtorzHaslo).sendKeys("SpringBootNajlepszy@1");

    }

}
