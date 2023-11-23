package pageObjects;

import common.Common;
import common.TestExectution;
import config.PropertiesFile;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;

public class ApiReqresPageObject extends Common {
    private static String apiBasePath = PropertiesFile.readProperties("apiReqresBasePath");

    public static void getListUsers(int numberOfPage) {
        RestAssured.given().log().all()
                .baseUri(apiBasePath)
                .basePath("/api/users/")
                .pathParam("pageNumber", numberOfPage)
                .contentType(ContentType.JSON)
                .when()
                .get("{pageNumber}")
                .then()
                .extract().response();
    }

    public static Response getDataById(int id) {
        return RestAssured.given().log().all()
                .baseUri(apiBasePath)
                .basePath("/api/users/")
                .pathParam("id", id)
                .contentType(ContentType.JSON)
                .when()
                .get("{id}")
                .then()
                .extract().response();
    }

    public static Response getDataById() {
        String id = TestExectution.paramStore.getParam("userId").toString();
        return RestAssured.given().log().all()
                .baseUri(apiBasePath)
                .basePath("/api/users/")
                .pathParam("id", id)
                .contentType(ContentType.JSON)
                .when()
                .get("{id}")
                .then()
                .extract().response();
    }

    public static void verifyNameById(Response response, String expectedName) {
        JsonPath jsonPath = new JsonPath(response.prettyPrint());
        String name = jsonPath.get("data.first_name");
        Assert.assertEquals("Name is incorrect ", expectedName, name);
    }

    public static void userWithIdshouldNotExist(Response response) {
        Assert.assertEquals("Status code invalid", 404, response.statusCode());
    }

    public static void userShouldExist(Response response) {
        verifyStatusCode(200, response.statusCode());
    }

    public static void postCreateNewUser(String body) {
        Response response = RestAssured.given().log().all()
                .baseUri(apiBasePath)
                .basePath("/api/users")
                .body(body)
                .contentType(ContentType.JSON)
                .post()
                .then()
                .extract().response();
        saveParamFromResponse("userId", response, "id");
        verifyStatusCode(201, response.statusCode());
    }

    public static String createUser(String name, String job) throws JsonProcessingException {
        return createUserBody(name, job);
    }

    public static String createAccount(String email, String password) throws JsonProcessingException {
        return createAccountBody(email, password);
    }

    public static void putCreateNewUser(String id, String body) {
        Response response = RestAssured.given().log().all()
                .baseUri(apiBasePath)
                .basePath("/api/users/")
                .pathParam("id", id)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .put("{id}")
                .then()
                .extract().response();
        verifyStatusCode(200, response.statusCode());
    }

    public static void putCreateNewUser(String body) {
        String id = TestExectution.paramStore.getParam("userId").toString();
        Response response = RestAssured.given().log().all()
                .baseUri(apiBasePath)
                .contentType(ContentType.JSON)
                .basePath(String.format("/api/users/%s", id)) // Different way we, dont want use pathparam here for example
                .body(body)
                .when()
                .put()
                .then()
                .extract().response();
        verifyStatusCode(200, response.statusCode());
    }

    public static void postCreateNewAccount(String body) {
        Response response = RestAssured.given().log().all()
                .baseUri(apiBasePath)
                .basePath("/api")
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/register")
                .then()
                .extract().response();
        saveParamFromResponse("id", response, "id");
        saveParamFromResponse("registerToken", response, "token");
    }
}
