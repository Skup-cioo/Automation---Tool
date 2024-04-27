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
import org.hamcrest.Matchers;
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
        List<Product> filteredList = listOfProduct.stream().filter(product -> product.getCategory().equals(category))
                .collect(Collectors.toList());

        System.out.println("Products with category coffee");
        for (Product p : filteredList) {
            System.out.println(p.toString());
        }
    }

    public static void getCheckApiStatus() {
        Response response = RestAssured.given().log().all()
                .baseUri(apiBasePath)
                .basePath("/status")
                .contentType(ContentType.JSON)
                .when()
                .get()
                .then()
                .assertThat()
                .body("status", Matchers.equalTo("UP")) // We can use RestAssured method -> But this is hard Assertion
                .log().all()
                .extract().response();

        Assert.assertEquals("Status is incorrect", checkIfFieldEquals(response, "status", "UP"), true);  // We can use our method which is not hard Assertion and has our description in case of fail
    }

    public static void postCreateNewClient(String body) {
        String accessToken = RestAssured.given().log().all()
                .baseUri(apiBasePath)
                .basePath("/api-clients")
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post()
                .then()
                .extract().path("accessToken");
        TestExectution.paramStore.setParam("accessToken", accessToken);
        // saveParamFromResponse("itemId", response, "itemId"); -> We can extract accessToken from RestAssured or extract full response and use this method
    }

    public static String createClient(String name, String email) throws JsonProcessingException {
        return createClientBody(name, email);
    }

    public static void postCreateBasket() {
        String cartId = RestAssured.given().log().all()
                .baseUri(apiBasePath)
                .basePath("/carts")
                .contentType(ContentType.JSON)
                .when()
                .post()
                .then()
                .extract().path("cartId");
        TestExectution.paramStore.setParam("cartId", cartId);
    }

    public static void getAllAvailableProducts() {
        Response response = RestAssured.given().log().all()
                .baseUri(apiBasePath)
                .basePath("/products")
                .contentType(ContentType.JSON)
                .when()
                .get()
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract().response();

        Common.verifyStatusCode(200, response.statusCode());
    }

    public static void getProductsWithCategory() throws JsonProcessingException {
        Response response = RestAssured.given().log().all()
                .baseUri(apiBasePath)
                .basePath("/products")
                .contentType(ContentType.JSON)
                .when()
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

        String itemId = RestAssured.given().log().all()
                .baseUri(apiBasePath)
                .basePath(String.format("/carts/%s/items", cardId))
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post()
                .then()
                .extract().path("itemId");

        TestExectution.paramStore.setParam("itemId", itemId);
    }

    public static void patchUpdateQuantity(Integer quantity) throws JsonProcessingException {
        bodyParmas = new HashMap<>();
        bodyParmas.put("quantity", quantity);
        String body = objectMapper.writeValueAsString(bodyParmas);

        String cardId = (String) TestExectution.paramStore.getParam("cartId");
        String itemId = TestExectution.paramStore.getParam("itemId").toString();

        RestAssured.given().log().all()
                .baseUri(apiBasePath)
                .basePath(String.format("/carts/%s/items/%s", cardId, itemId))
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .patch()
                .then()
                .extract().response();
    }

    public static void getBasket() {
        String cardId = (String) TestExectution.paramStore.getParam("cartId");
        RestAssured.given().log().all()
                .baseUri(apiBasePath)
                .basePath(String.format("/carts/%s", cardId))
                .contentType(ContentType.JSON)
                .when()
                .get()
                .then()
                .extract().response();
    }
}
