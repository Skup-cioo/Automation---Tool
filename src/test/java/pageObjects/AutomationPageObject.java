package pageObjects;

import common.Common;
import common.NotFoundException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class AutomationPageObject extends Common {
    private static final By pageTitleText = By.xpath("//h1[normalize-space()='Your Website to practice Automation Testing']");
    private static final By firstNameTextField = By.name("fname");
    private static final By lastNameTextField = By.name("lname");
    private static final By allInputsAvailableOnPage = By.xpath("//input");
    private static final By sliderElement = By.id("a");
    private static final By submitButton = By.xpath("//button[normalize-space()='Submit']");
    private static final By quantityInput = By.id("quantity");
    private static final By dayElement = By.id("day");

    private static WebElement optionInListDropDown;

    private static WebElement checkBoxOptionByName(String name) {
        return driver.findElement(By.xpath(String.format("//input[@value='%s']", name)));
    }

    public static void pageIsDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(pageTitleText)));
    }

    public static void fillFirstNameAndLastName(String firstName, String lastName) {
        driver.findElement(firstNameTextField).sendKeys(firstName);
        driver.findElement(lastNameTextField).sendKeys(lastName);
    }

    public static void chooseGender(String gender) {
        driver.findElements(allInputsAvailableOnPage).stream()
                .filter(element -> element.getAttribute("id").equals(gender))
                .findFirst().get().click();
    }

    public static void selectOneOption(int option) {
        optionInListDropDown = driver.findElement(By.id("option"));
        select = new Select(optionInListDropDown);
        select.selectByIndex(option);
    }

    public static void selectMultiplyOptions(int numberOfOptions, int startOption) {
        optionInListDropDown = driver.findElement(By.xpath("//select[@id='owc']"));
        select = new Select(optionInListDropDown);
        startOption--;
        for (int i = startOption; i < numberOfOptions + startOption; i++) {
            actions.keyDown(Keys.LEFT_CONTROL).build().perform();
            select.selectByIndex(i);
        }
    }

    public static void selectMultiplyOptions(int numberOfOptions) {
        selectMultiplyOptions(numberOfOptions, 1);
    }

    public static void selectOptionByName(String name) {
        checkBoxOptionByName(name).click();
    }

    public static void selectDate(String date) {
//        List<String> listOfSplitedDate = new ArrayList<>();
//        StringBuilder bufor = new StringBuilder();
//        for (int i = 1; i < 4; i++) {
//            listOfSplitedDate.add(getTextFromStringByRegex(date, "(\\d{2})-(\\d{2})-(\\d{4})", i));
//            bufor.append(getTextFromStringByRegex(date, "(\\d{2})-(\\d{2})-(\\d{4})", i));
//        }
//        driver.findElement(dayElement).sendKeys(bufor);
    }

    public static void moveSliderTo(double value) {
        WebElement slider = driver.findElement(sliderElement);
        slider.click();
        int width = slider.getSize().getWidth();
        int bufor = (int) ((width - 20) * value);
        int targetPlace;
        if (value < 0.51) {
            targetPlace = ((width - 20) / 2 - bufor) * (-1);
        } else {
            targetPlace = bufor - ((width - 20) / 2);
        }
        actions.clickAndHold(slider).moveByOffset(targetPlace, 0).release().build().perform();
    }

    public static void clickButton() {
        try {
            Common.clickIfExist(driver.findElement(submitButton));
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }
}
