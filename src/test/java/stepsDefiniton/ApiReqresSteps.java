package stepsDefiniton;

import pageObjects.ApiReqresPageObject;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class ApiReqresSteps {
    private static final String APP_AND_PAGE_NAME = "[REQRES][API] ";

    @Given(APP_AND_PAGE_NAME + "get '{int}' user list page")
    public void getAllUsers(int numberOfPage) {
        ApiReqresPageObject.getListUsers(numberOfPage);
    }

    @When(APP_AND_PAGE_NAME + "check if user with id '{}' has first name '{}'")
    public void verifyNameById(int id, String name) {
        ApiReqresPageObject.verifyNameById(ApiReqresPageObject.getDataById(id), name);
    }

    @And(APP_AND_PAGE_NAME + "verify if user with id '{}' does not exist")
    public void verifyIfUserWithIdExist(int id) {
        ApiReqresPageObject.userWithIdshouldNotExist(ApiReqresPageObject.getDataById(id));
    }

    @Given(APP_AND_PAGE_NAME + "create new user with name '{}' and job '{}'")
    public void postCreateNewUserWithNameAndJob(String name, String job) throws JsonProcessingException {
        ApiReqresPageObject.postCreateNewUser(ApiReqresPageObject.createUser(name, job));
    }

    @Given(APP_AND_PAGE_NAME + "update user's name '{}' and job '{}' for user id '{}'")
    public void updateUserWithIdNewNameAndJob(String name, String job, String id) throws JsonProcessingException {
        ApiReqresPageObject.putCreateNewUser(id, ApiReqresPageObject.createUser(name, job));
    }
}
