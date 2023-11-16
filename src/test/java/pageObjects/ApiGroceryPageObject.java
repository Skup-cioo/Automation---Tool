package pageObjects;

import common.Common;
import common.TestExectution;
import common.ownResources.Product;
import config.PropertiesFile;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.core.type.TypeReference;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class ApiGroceryPageObject extends Common {
    private static String apiBasePath = PropertiesFile.readProperties("apiGroceryStoreBasePath");
    private static Map<String, Integer> bodyParmas;
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void printProductsWithCategory(String category) {
        List<Product> listOfProduct = (List) TestExectution.paramStore.getParam("listOfProducts");
        List<Product> filteredList = listOfProduct.stream().filter(x -> x.getCategory().equals(category))
                .collect(Collectors.toList());

        System.out.println("Products with category coffee");
        for (Product p : filteredList) {
            System.out.println(p.toString());
        }
    }

    public static void getCheckApiStatus() {
        Response response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(apiBasePath)
                .basePath("/status")
                .get()
                .then()
                .extract().response();
        Assert.assertEquals("Status is incorrect", checkIfFieldEquals(response, "status", "UP"), true);
    }

    public static void postCreateNewClient(String body) {
        Response response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(apiBasePath)
                .basePath("/api-clients")
                .body(body)
                .post()
                .then()
                .extract().response();
        saveParamFromResponse("accessToken", response, "accessToken");
    }

    public static String createClient(String name, String email) throws JsonProcessingException {
        return createClientBody(name, email);
    }

    public static void postCreateBasket() {
        Response response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(apiBasePath)
                .basePath("/carts")
                .post()
                .then()
                .extract().response();
        saveParamFromResponse("cartId", response, "cartId");
    }

    public static void getAllAvailableProducts() {
        Response response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(apiBasePath)
                .basePath("/products")
                .get()
                .then()
                .extract().response();
        Common.verifyStatusCode(200, response.statusCode());
    }

    public static void getProductsWithCategory() throws JsonProcessingException {
        Response response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(apiBasePath)
                .basePath("/products")
                .get()
                .then()
                .extract().response();
        saveProductsToParam(response);
    }

    public static void saveProductsToParam(Response response) throws JsonProcessingException {
        String jsonResponse = response.body().prettyPrint();
        ObjectMapper objectMapper = new ObjectMapper();
        List<Product> listOfProducts = objectMapper.readValue(jsonResponse, new TypeReference<>() {
        });

        TestExectution.paramStore.setParam("listOfProducts", listOfProducts);
    }

    public static void postAddProductToTheBasket(Integer id, Integer quantity) throws JsonProcessingException {
        bodyParmas = new HashMap<>();
        bodyParmas.put("quantity", quantity);
        bodyParmas.put("productId", id);

        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(bodyParmas);
        String cardId = (String) TestExectution.paramStore.getParam("cartId");

        Response response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(apiBasePath)
                .basePath("/carts/" + cardId + "/items")
                .body(body)
                .post()
                .then()
                .extract().response();

        saveParamFromResponse("itemId", response, "itemId");
    }

    public static void patchUpdateQuantity(Integer quantity) throws JsonProcessingException {
        bodyParmas = new HashMap<>();
        bodyParmas.put("quantity", quantity);
        String body = objectMapper.writeValueAsString(bodyParmas);

        String cardId = (String) TestExectution.paramStore.getParam("cartId");
        String itemId = TestExectution.paramStore.getParam("itemId").toString();

        Response response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(apiBasePath)
                .basePath("/carts/" + cardId + "/items/" + itemId)
                .body(body)
                .patch()
                .then()
                .extract().response();
        System.out.println("update" + response.prettyPrint());
    }

    public static void getBasket() {
        String cardId = (String) TestExectution.paramStore.getParam("cartId");
        Response response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(apiBasePath)
                .basePath("/carts/" + cardId)
                .get()
                .then()
                .extract().response();
        System.out.println(response.prettyPrint());
    }
}
