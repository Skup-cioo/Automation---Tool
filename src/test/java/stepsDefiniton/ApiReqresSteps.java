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

    @And(APP_AND_PAGE_NAME + "verify if recently created user exist")
    public void verifyIfUserExist() {
        ApiReqresPageObject.userShouldExist(ApiReqresPageObject.getDataById());
    }

    @Given(APP_AND_PAGE_NAME + "create new user with name '{}' and job '{}'")
    public void postCreateNewUserWithNameAndJob(String name, String job) throws JsonProcessingException {
        ApiReqresPageObject.postCreateNewUser(ApiReqresPageObject.createUser(name, job));
    }

    @Given(APP_AND_PAGE_NAME + "update user's name '{}' and job '{}' for user id '{}'")
    public void updateUserWithIdNewNameAndJob(String name, String job, String id) throws JsonProcessingException {
        ApiReqresPageObject.putCreateNewUser(id, ApiReqresPageObject.createUser(name, job));
    }

    @Given(APP_AND_PAGE_NAME + "update user's name '{}' and job '{}' for recently created user")
    public void updateUserWithIdNewNameAndJob(String name, String job) throws JsonProcessingException {
        ApiReqresPageObject.putCreateNewUser(ApiReqresPageObject.createUser(name, job));
    }

    @Given(APP_AND_PAGE_NAME + "register new account with email '{}' and password '{}'")
    public void createNewAccount(String email, String password) throws JsonProcessingException {
        ApiReqresPageObject.postCreateNewAccount(ApiReqresPageObject.createAccount(email, password));
    }
}
