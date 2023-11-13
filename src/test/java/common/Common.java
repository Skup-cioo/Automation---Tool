package common;

import config.PropertiesFile;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Common {
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected static Actions actions;
    protected static Select select;
    protected static Boolean driverExist = false;
    private static String path = "C:\\AutomationProject";

    public static void createDriver() {
        System.setProperty("webdriver.chromer.driver", path + "\\src\\main\\resources\\geckodriver.exe"); //depends which browser do you use
        driver = new FirefoxDriver();
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driverExist = true;
    }

    public static void openApp(String nameOfApp) {
        switch (AppName.getApplicationName(nameOfApp)) {
            case GOOGLE:
                driver.get(PropertiesFile.readProperties("googleURL"));
                break;
            case MOUSE:
                driver.get(PropertiesFile.readProperties("mouseURL"));
                break;
            case AUTOMATION:
                driver.get(PropertiesFile.readProperties("automationURL"));
                break;
        }
    }

    public static void takeScreenShotForDriver() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM_dd_HH_mm");
        LocalDateTime now = LocalDateTime.now();
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File(path + "\\target\\Screens\\screen_" + dtf.format(now) + ".jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void dragAndDropElement(By draggable, By droppable) {
        WebElement draggableWebElement = driver.findElement(draggable);
        WebElement droppableWebElement = driver.findElement(droppable);
        actions.dragAndDrop(draggableWebElement, droppableWebElement).build().perform();
    }

    public static void dragAndDropBy(By draggable, int x, int y) {
        WebElement draggableWebElement = driver.findElement(draggable);
        actions.dragAndDropBy(draggableWebElement, x, y).build().perform();
    }

    public static void clickAndHold(WebElement firstLetterElement, WebElement secondLetterElement) {
        actions.moveToElement(firstLetterElement);
        actions.clickAndHold();
        actions.moveToElement(secondLetterElement).release();
        actions.build().perform();
    }

    public static void waitSomeTime(Timeouts time) {
        try {
            Thread.sleep(time.getMilliseconds());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static By elementContainsText(String text) {
        return By.xpath(String.format("//a[normalize-space()='%s']", text));
    }

    public static void maxWindow() {
        driver.manage().window().maximize();
    }

    public static void clickIfExist(WebElement element) throws NotFoundException {
        if (element.isDisplayed()) {
            element.click();
        } else {
            throw new NotFoundException("Can't click this element");
        }
    }

    public static String createUsersBody(String name, String job) throws JsonProcessingException {
        User user = new User();
        user.setName(name);
        user.setJob(job);
        List<User> userList = new ArrayList<>();
        userList.add(user);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(userList);
    }

    public static String getTextFromStringByRegex(String allText, String regex, int index) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(allText);
        if (matcher.find()) {
            return matcher.group(index);
        }
        return "No text was found matching the value you were looking for";
    }
}
