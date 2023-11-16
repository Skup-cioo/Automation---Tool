package stepsDefiniton;

import common.ownResources.GeneratorPolishName;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.*;
import pageObjects.ApiGroceryPageObject;

public class ApiGrocerySteps {
    private static final String APP_AND_PAGE_NAME = "[GROCERY][API] ";

    @Given(APP_AND_PAGE_NAME + "check api status")
    public void getAllUsers() {
        ApiGroceryPageObject.getCheckApiStatus();
    }

    @Given(APP_AND_PAGE_NAME + "create new client with name '{}' and email '{}'")
    public void postCreateNewClient(String name, String email) throws JsonProcessingException {
        if (name.equals("RANDOM_NAME")) {
            name = GeneratorPolishName.getRandomName();
        }
        if (email.equals("RANDOM_EMAIL")) {
            email = GeneratorPolishName.getRandomEmail();
        }
        ApiGroceryPageObject.postCreateNewClient(ApiGroceryPageObject.createClient(name, email));
    }

    @Given(APP_AND_PAGE_NAME + "create basket")
    public void postCreateBasket() {
        ApiGroceryPageObject.postCreateBasket();
    }

    @Given(APP_AND_PAGE_NAME + "get all available products")
    public void getAllAvailableProducts() {
        ApiGroceryPageObject.getAllAvailableProducts();
    }

    @Given(APP_AND_PAGE_NAME + "get all available products where category '{}'")
    public void getAllAvailableProductsByCategory(String category) throws JsonProcessingException {
        ApiGroceryPageObject.getProductsWithCategory();
        ApiGroceryPageObject.printProductsWithCategory(category);
    }

    @And(APP_AND_PAGE_NAME + "add item with id '{}' and quantity '{}' to the basket")
    public void putAddProductToTheBasket(Integer id, Integer quantity) throws JsonProcessingException {
        ApiGroceryPageObject.postAddProductToTheBasket(id, quantity);
    }

    @Then(APP_AND_PAGE_NAME + "update product quantity to '{}' in basket")
    public void patchChangeQuantityForProduct(Integer quantity) throws JsonProcessingException {
        ApiGroceryPageObject.patchUpdateQuantity(quantity);
    }
    @Then(APP_AND_PAGE_NAME + "check user basket")
    public void getCheckBasket() {
        ApiGroceryPageObject.getBasket();
    }
}
