package stepsDefiniton;

import common.ownResources.GeneratorPolishName;
import io.cucumber.java.en.Then;
import pageObjects.AutomationPageObject;
import pageObjects.o2PageObject;

import java.util.Random;

public class o2Steps {

    private static final String APP_AND_PAGE_NAME = "[o2] ";
    String name = GeneratorPolishName.getRandomName();
    String naz = GeneratorPolishName.getRandomLastName();

//    @Then(APP_AND_PAGE_NAME + "user put name '{}' and last name '{}'")
//    public void putTextToInput(String firstName, String lastName) {
//        if (firstName.equals("RANDOM_NAME")) {
//            firstName = name;
//        }
//        if (lastName.equals("RANDOM_LAST_NAME")) {
//            lastName = naz;
//        }
//        o2PageObject.fillFirstNameAndLastName(firstName, lastName);
//    }
//
//
//    @Then(APP_AND_PAGE_NAME + "user put login")
//    public void putEmailLogin() {
//        Random rand = new Random();
//        String login = name + naz + rand.nextInt(30);
//        o2PageObject.putlogin(login);
//    }
}
